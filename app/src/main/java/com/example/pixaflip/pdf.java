package com.example.pixaflip;

public class pdf {
    private String name, url, description;
    private boolean isfav;

    public pdf(String name, String url, String description) {
        this.name = name;
        this.url = url;
        this.description = description;
        isfav = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsfav() {
        return isfav;
    }

    public void setIsfav(boolean isfav) {
        this.isfav = isfav;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
