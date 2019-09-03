package com.waw.hr.mutils.bean;

import java.io.Serializable;

public class WordListBean implements Serializable {


    /**
     * id : 1
     * type_id : 1
     * title : hello
     * comment : 你好--喂
     */

    private int id;
    private int type_id;
    private String title;
    private String comment;

    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
