package com.devsenior.nmanja.interfaces;

import com.devsenior.nmanja.model.Book;

public interface BookRepository {

    void saveBook(Book book);
    Book findById(String id);
    

}
