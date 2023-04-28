package com.fit2081.week6labtask.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {
    @Query("SELECT * FROM books")
    LiveData<List<Book>> getAllBooks();

    @Insert
    void addBook(Book book);

    @Query("SELECT DatabaseID FROM books ORDER BY DatabaseID DESC LIMIT 1")
    int getLastBook();

    @Query("delete FROM books")
    void deleteAllBooks();

    @Query("DELETE FROM books WHERE BookPrice < 100")
    void deleteBooksPricedLowerThan100();


    @Query("DELETE FROM books WHERE DatabaseID = :lastBookId")
    void deleteLastBook(int lastBookId);

}