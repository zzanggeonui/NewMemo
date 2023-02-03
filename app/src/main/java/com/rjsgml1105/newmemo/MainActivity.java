package com.rjsgml1105.newmemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.rjsgml1105.newmemo.adapter.memoAdapter;
import com.rjsgml1105.newmemo.data.DatabaseHandler;
import com.rjsgml1105.newmemo.model.Memo;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    EditText editSearch;
    ImageView imgSearch;
    ImageView imgDelete;

    RecyclerView recyclerView;
    memoAdapter adapter;
    ArrayList<Memo> memoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btnAdd);
        editSearch = findViewById(R.id.editSearch);
        imgDelete = findViewById(R.id.imgDelete);
        imgSearch = findViewById(R.id.imgSearch);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setHasFixedSize(true);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // 메모 추가 액티비티 실행
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

        // 앱 실행시 저장된 데이터를 화면에 보여준다
        // db에 저장된 데이터를 가져온다
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        memoList = db.getAllMemos();


        adapter = new memoAdapter(MainActivity.this, memoList);

        recyclerView.setAdapter(adapter);


//        imgSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String keyword = editSearch.getText().toString().trim();
//                if(keyword.isEmpty()){
//                    Toast.makeText(MainActivity.this, "입력좀", Toast.LENGTH_SHORT).show();
//
//                    editSearch.setText("");
//                    return;
//                }
//                // db에서 검색한다.
//
//                DatabaseHandler db = new DatabaseHandler(MainActivity.this);
//                memoList = db.searchMemo(keyword);
//
//                adapter = new memoAdapter(MainActivity.this, memoList);
//
//                recyclerView.setAdapter(adapter);
//
//                // 화면에 표시
//
//
//            }
//        });



        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 저장된 모든 메모를 가져온다.
                DatabaseHandler db = new DatabaseHandler(MainActivity.this);
                memoList = db.getAllMemos();
                adapter = new memoAdapter(MainActivity.this, memoList);

                recyclerView.setAdapter(adapter);

                //기존에 입력된 검색어도 삭제
                editSearch.setText("");
            }
        });


        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // 유저가 입력한 키워드 뽑아서
                String keyword = editSearch.getText().toString().trim();

                // 2글자 이상 입력했을때만, 검색이 되도록 한다.
                if (keyword.length() <2 && keyword.length() >0 ){
                    return;
                }


                //디비에 가저온후
                DatabaseHandler db = new DatabaseHandler(MainActivity.this);
                memoList = db.searchMemo(keyword);


                //화면에 보여준다.

                adapter = new memoAdapter(MainActivity.this, memoList);

                recyclerView.setAdapter(adapter);


            }
        });





    }

}