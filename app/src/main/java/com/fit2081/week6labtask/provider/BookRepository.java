package com.fit2081.week6labtask.provider;
import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;
import java.util.concurrent.Executors;

public class BookRepository {

    private BookDao bookDao;
    private LiveData<List<Book>> allBooks;

    BookRepository(Application application) {
        BookDatabase db = Room.databaseBuilder(application, BookDatabase.class, "book_database").build();
        bookDao = db.bookDao();
        allBooks = bookDao.getAllBooks();
    }

    LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    void insert(Book book) {
        //new InsertBookAsyncTask(bookDao).execute(book);
        BookDatabase.databaseWriteExecutor.execute(() -> bookDao.addBook(book));

    }
    void deleteAll() {
        BookDatabase.databaseWriteExecutor.execute(() -> {
            bookDao.deleteAllBooks();
        });
    }
    void delete100(){
        BookDatabase.databaseWriteExecutor.execute(()->{
            bookDao.deleteBooksPricedLowerThan100();
        });
    }

    public void deleteLastBook() {
        BookDatabase.databaseWriteExecutor.execute(() -> {
            int lastBookId = bookDao.getLastBook();
            bookDao.deleteLastBook(lastBookId);
        });
    }
    /*private static class InsertBookAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDao bookDao;

        private InsertBookAsyncTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDao.insert(books[0]);
            return null;
        }
    }*/
    }
