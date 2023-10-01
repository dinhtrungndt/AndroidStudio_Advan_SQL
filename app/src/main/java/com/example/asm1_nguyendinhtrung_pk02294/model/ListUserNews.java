package com.example.asm1_nguyendinhtrung_pk02294.model;

import java.util.ArrayList;

public class ListUserNews {
    private String title;
    private ArrayList<UserNews> data;

    public ListUserNews(String title, ArrayList<UserNews> data) {
        this.title = title;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<UserNews> getData() {
        return data;
    }

    public void setData(ArrayList<UserNews> data) {
        this.data = data;
    }
}
