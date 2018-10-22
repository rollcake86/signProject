package com.dreamsecurity.signproject.activity.view;

public class SignItem {

    private String title;
    private String content;
    private int kind;
    private String signCount;
    private String author;
    private String signHash;

    public SignItem(String title, String content, int kind, String signCount, String author , String signHash) {
        this.title = title;
        this.content = content;
        this.kind = kind;
        this.signCount = signCount;
        this.author = author;
        this.signHash = signHash;
    }


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getKind() {
        return kind;
    }

    public String getSignCount() {
        return signCount;
    }

    public String getAuthor() {
        return author;
    }

    public String getSignHash() {
        return signHash;
    }
}
