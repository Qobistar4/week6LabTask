package com.fit2081.week6labtask.provider;

import static com.fit2081.week6labtask.provider.Book.TABLE_NAME;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = TABLE_NAME)
public class Book {

    public static final String TABLE_NAME = "books";
    private static int idCounter = 1;

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "DatabaseID")
    private int registerID;
    @ColumnInfo(name = "BookID")
    private String id;
    @ColumnInfo(name = "BookTitle")
    private String title;
    @ColumnInfo(name = "BookISBN")
    private String isbn;
    @ColumnInfo(name = "BookAuthor")
    private String author;
    @ColumnInfo(name = "BookDesc")
    private String description;
    @ColumnInfo(name = "BookPrice")
    private float price;

    public Book(String id, String title, String author, String isbn, String description, float price) {
        this.registerID= idCounter++;
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.description = description;
        this.price = price;

    }


    // Getters
    public int getRegisterID() {
        return registerID;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    // Setters
    public void setRegisterID(int registerID){
        this.registerID=registerID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
