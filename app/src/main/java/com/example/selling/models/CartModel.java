package com.example.selling.models;

public class CartModel {

    String productName;
    String productPrice;
    int totalPrice;
    String documentId;

    public CartModel() {
    }

    public CartModel(String productName, String productPrice, int totalPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.totalPrice = totalPrice;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
