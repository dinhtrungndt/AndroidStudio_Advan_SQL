package com.example.asm1_nguyendinhtrung_pk02294.model;

import java.io.Serializable;

public class ThongTin implements Serializable {
    private String name;
    private String date;
    private int id; // thêm vô

    public ThongTin(String name, String date, int id) {
        this.name = name;
        this.date = date;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
