package com.fit2081.week6labtask.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BookViewModel extends AndroidViewModel {

    private BookRepository repository;
    private LiveData<List<Book>> allBooks;

    public BookViewModel(@NonNull Application application) {
        super(application);
        repository = new BookRepository(application);
        allBooks = repository.getAllBooks();
    }

    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    public void insert(Book book) {
        repository.insert(book);
    }
    public void deleteAll(){
        repository.deleteAll();
    }
    public void delete100() {repository.delete100();}
    public void deleteLastBook() {
        repository.deleteLastBook();
    }


}

