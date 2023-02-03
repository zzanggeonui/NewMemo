package com.rjsgml1105.newmemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rjsgml1105.newmemo.data.DatabaseHandler;
import com.rjsgml1105.newmemo.model.Memo;

public class AddActivity extends AppCompatActivity {

    EditText editTitle;
    EditText editContents;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editTitle = findViewById(R.id.editTitle);
        editContents = findViewById(R.id.editContents);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String title = editTitle.getText().toString().trim();
                String contents = editContents.getText().toString().trim();

                if(title.isEmpty() || contents.isEmpty() ){
                    Toast.makeText(AddActivity.this, "다입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                //메모를 저장한다.
            Memo memo = new Memo(title,contents);

                // 위의 메모를 디비에 저장한다
                DatabaseHandler db = new DatabaseHandler(AddActivity.this);
                db.addMemo(memo);

                // 다 완료되면, 메모 생성 화면은 필요가 없다. 따라서 이액티비티는 종료한다
                finish();

            }
        });

    }
}