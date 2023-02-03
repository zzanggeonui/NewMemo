package com.rjsgml1105.newmemo.model;

public class Memo {
    public int id;
    public static String title;
    public static String contents;

    public Memo(){

    }

    public Memo(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Memo(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.contents = content;

    }
}
