package com.rjsgml1105.newmemo.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.rjsgml1105.newmemo.model.Memo;
import com.rjsgml1105.newmemo.util.Util;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DB_NAME, null, Util.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 테이블 생성
        String CREATE_CONTACT_TABLE = "create table memo ( id integer primary key, title text, content,text    )";

        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // 기존의 테이블을 삭제하고, 새 테이블을 다시 만든다.
        String DROP_TABLE = "drop table memo";
        sqLiteDatabase.execSQL(DROP_TABLE);
//
//        sqLiteDatabase.execSQL(DROP_TABLE,new String[]{Util.DA_NAME});
//              이렇게 써도 됨

        onCreate(sqLiteDatabase);
    }

    public void addMemo(Memo memo) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "insert into memo" + "(title,content)" + "values" + "(?,?)";
        String[] args = new String[]{memo.title, memo.contents};

        db.execSQL(query, args);
        db.close();


    }

    public ArrayList<Memo> getAllMemos() {


        // 1. 데이터베이스를 가져온다.
        SQLiteDatabase db = this.getReadableDatabase();

        //2. 쿼리문 만든다.
        String query = "select * from memo order by id desc";

        //3. 쿼리문을 실행하여, 커서로 받는다.
        Cursor cursor = db.rawQuery(query, null);

        // 3-1. 여러 데이터를 저장할 어레이 리스트를 만든다.
        ArrayList<Memo> memoArrayList = new ArrayList<>();

        // 4. 커서에서 데이터를 뽑아낸다.
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String content = cursor.getString(2);

                Log.i("Contact_TABLE", id + "," + title + "," + content);

                // 이 데이터를, 화면에 표시하기 위해서는 메모리에 전부다 남아있어야한다. 묶음처리

                Memo memo = new Memo(id, title, content);
                memoArrayList.add(memo);


            } while (cursor.moveToNext());
        }

        //5. db사용 끝났으니 닫기
        db.close();

        //6. db에서 읽어온 정보를 메모리에 리턴해줘야함
        return memoArrayList;

    }


    public ArrayList<Memo> searchMemo(String keyword) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "select * from memo where content like '%"+keyword+"%' or title like '%"+keyword+"%'";
        Cursor cursor = db.rawQuery(query, null);

        // 3-1. 여러 데이터를 저장할 어레이 리스트를 만든다.
        ArrayList<Memo> memoArrayList = new ArrayList<>();

        // 4. 커서에서 데이터를 뽑아낸다.
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String content = cursor.getString(2);

                Log.i("Contact_TABLE", id + "," + title + "," + content);

                // 이 데이터를, 화면에 표시하기 위해서는 메모리에 전부다 남아있어야한다. 묶음처리

                Memo memo = new Memo(id, title, content);
                memoArrayList.add(memo);


            } while (cursor.moveToNext());
        }

        //5. db사용 끝났으니 닫기
        db.close();

        //6. db에서 읽어온 정보를 메모리에 리턴해줘야함
        return memoArrayList;

    }
}