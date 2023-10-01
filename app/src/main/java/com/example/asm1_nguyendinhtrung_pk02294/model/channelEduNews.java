package com.example.asm1_nguyendinhtrung_pk02294.model;

import java.util.ArrayList;

public class channelEduNews {
    private String title;
    private String description;
    private imageEduNews image;
    private String pubDate;
    private String generator;
    private String link;
    private ArrayList<itemEduUserNews> item;

    public channelEduNews(String title, String description, imageEduNews image, String pubDate, String generator, String link, ArrayList<itemEduUserNews> item) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.pubDate = pubDate;
        this.generator = generator;
        this.link = link;
        this.item = item;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public imageEduNews getImage() {
        return image;
    }

    public void setImage(imageEduNews image) {
        this.image = image;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ArrayList<itemEduUserNews> getItem() {
        return item;
    }

    public void setItem(ArrayList<itemEduUserNews> item) {
        this.item = item;
    }
}
