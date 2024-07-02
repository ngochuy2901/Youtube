package com.example.youtube.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Video implements Serializable {
    private String id;
    private String title;
    private String description;
    private String url;
    private Date timeUpload;
    private int view;
    private int like;
    private int dislike;
//    private List<String> comment;
    private Category category;
    private String userId;

    public Video(String id, String title, String description, String url, Date timeUpload, int view, int like, int dislike, Category category, String userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.timeUpload = timeUpload;
        this.view = view;
        this.like = like;
        this.dislike = dislike;
//        this.comment = comment;
        this.category = category;
        this.userId = userId;
    }

    public Video(String title, String description, String url, Date timeUpload, int view, int like, int dislike, Category category, String userId) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.timeUpload = timeUpload;
        this.view = view;
        this.like = like;
        this.dislike = dislike;
//        this.comment = comment;
        this.category = category;
        this.userId = userId;
    }

    public Video(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public Video() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getTimeUpload() {
        return timeUpload;
    }

    public void setTimeUpload(Date timeUpload) {
        this.timeUpload = timeUpload;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

//    public List<String> getComment() {
//        return comment;
//    }
//
//    public void setComment(List<String> comment) {
//        this.comment = comment;
//    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
