package com.fit2081.week6labtask;


public class Item {
    private String itemID;
    private String itemTitle;
    private String itemAuthor;
    private String itemISBN;
    private String itemDesc;
    private float itemPrice;

    public Item(String itemID, String itemTitle, String itemAuthor, String itemISBN,String itemDesc,float itemPrice) {
        this.itemTitle = itemTitle;
        this.itemID = itemID;
        this.itemAuthor = itemAuthor;
        this.itemISBN = itemISBN;
        this.itemDesc = itemDesc;
        this.itemPrice = itemPrice;

    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemAuthor() {
        return itemAuthor;
    }

    public void setItemAuthor(String itemAuthor) {
        this.itemAuthor = itemAuthor;
    }

    public String getItemISBN() {
        return itemISBN;
    }

    public void setItemISBN(String itemISBN) {
        this.itemISBN = itemISBN;
    }
    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Float itemPrice) {
        this.itemPrice = itemPrice;
    }
}