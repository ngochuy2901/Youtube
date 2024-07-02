package com.example.youtube.Model;

import java.io.Serializable;

public class Category implements Serializable {
    private String id;
    private String name;
    private String urlImage;
    private String description;
    private int numberOfVideo;

    public Category(String id, String name, String url, String description, int numberOfVideo) {
        this.id = id;
        this.name = name;
        this.urlImage = url;
        this.description = description;
        this.numberOfVideo = numberOfVideo;
    }

    public Category(String name, String url, String description, int numberOfVideo) {
        this.name = name;
        this.urlImage = url;
        this.description = description;
        this.numberOfVideo = numberOfVideo;
    }

    public Category() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return urlImage;
    }

    public void setUrl(String url) {
        this.urlImage = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfVideo() {
        return numberOfVideo;
    }

    public void setNumberOfVideo(int numberOfVideo) {
        this.numberOfVideo = numberOfVideo;
    }
}
