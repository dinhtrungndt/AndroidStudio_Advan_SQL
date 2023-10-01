package com.example.asm1_nguyendinhtrung_pk02294.model;

import java.util.ArrayList;

public class itemEduUserNews {
    private String title;
    private descriptionNewsEdu description;
    private String pubDate;
    private String link;
    private String guid;
    private int comments;

    public itemEduUserNews(String title, descriptionNewsEdu description, String pubDate, String link, String guid, int comments) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.link = link;
        this.guid = guid;
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public descriptionNewsEdu getDescription() {
        return description;
    }

    public void setDescription(descriptionNewsEdu description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
