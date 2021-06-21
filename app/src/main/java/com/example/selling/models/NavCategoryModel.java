package com.example.selling.models;

public class NavCategoryModel {
    String category_name;
    String img_url;

    public NavCategoryModel() {
    }

    public NavCategoryModel(String category_name, String img_url) {
        this.category_name = category_name;
        this.img_url = img_url;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}


