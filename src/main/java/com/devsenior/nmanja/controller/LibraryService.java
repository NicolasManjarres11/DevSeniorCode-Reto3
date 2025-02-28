package com.devsenior.nmanja.controller;

import java.util.ArrayList;
import java.util.List;

import com.devsenior.nmanja.exceptions.BookNotFoundException;
import com.devsenior.nmanja.exceptions.UserNotFounfException;
import com.devsenior.nmanja.interfaces.BookRepository;
import com.devsenior.nmanja.interfaces.LoanRepository;
import com.devsenior.nmanja.model.Book;
import com.devsenior.nmanja.model.Loan;
import com.devsenior.nmanja.model.User;

public class LibraryService {

    private BookRepository bookRepository;
    private LoanRepository loanRepository;
    private List<User> users;


    public LibraryService(BookRepository bookRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
        users = new ArrayList<>();

    }


    public void addBook(String id, String title, String author) {
        bookRepository.saveBook(new Book(id, title, author)); //Esta linea se mockea
    }

    public Book getBookById(String id) throws BookNotFoundException{

        if(bookRepository.findById(id) == null){
            throw new BookNotFoundException("Libro no encontrado");
        }

        return bookRepository.findById(id);

    }

    public void addUser(String id, String name){
        users.add(new User(id, name));
    }

    public void borrowBook(String userId, String bookId){

        for(User user : users){

            if(user.getId().equals(userId)){
                var book = bookRepository.findById(bookId);
                loanRepository.saveLoan(new Loan(user, book));
                book.setBorrowed(true);
            } else {
                throw new UserNotFounfException("No se encontró el usuario con el siguiente Id: " + userId);
            }
        }

    }

    public Loan getLoanByUserId(String userid){

        if(loanRepository.findById(userid) == null){
            throw new UserNotFounfException("No se encontró el usuario con el siguiente Id: " + userid);
        }

        return loanRepository.findById(userid);
    }
        
        





}
