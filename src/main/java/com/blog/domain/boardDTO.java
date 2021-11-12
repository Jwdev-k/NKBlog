package com.blog.domain;

import java.util.Date;

public class boardDTO {
    private int bno;
    private String title;
    private String uid;
    private Date created;
    private String content;
    private int available;

    public boardDTO(int bno, String title, String uid, Date created, String content, int available) {
        this.bno = bno;
        this.title = title;
        this.uid = uid;
        this.created = created;
        this.content = content;
        this.available = available;
    }

    @Override
    public String toString() {
        return "boardDTO{" +
                "bno=" + bno +
                ", title='" + title + '\'' +
                ", uid='" + uid + '\'' +
                ", created=" + created +
                ", content='" + content + '\'' +
                ", Available=" + available +
                '}';
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        available = available;
    }
}
