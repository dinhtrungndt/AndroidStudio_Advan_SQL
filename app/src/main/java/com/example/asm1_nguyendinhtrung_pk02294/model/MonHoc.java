package com.example.asm1_nguyendinhtrung_pk02294.model;

import java.io.Serializable;

public class MonHoc implements Serializable {
    private String code;
    private String name;
    private String teacher;
    private int id; // thêm vô

    public MonHoc(String code, String name, String teacher, int id) {
        this.code = code;
        this.name = name;
        this.teacher = teacher;
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
