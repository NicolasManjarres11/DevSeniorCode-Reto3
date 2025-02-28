package com.devsenior.nmanja.model;

import java.util.Date;

public class Loan {

    private final Book book;
    private final User user;
    private final Date loanDate;


    public Loan( User user,Book book) {
        this.user = user;
        this.book = book;
        this.loanDate = new Date();
    }


    public Book getBook() {
        return book;
    }


    public User getUser() {
        return user;
    }


    public Date getLoanDate() {
        return loanDate;
    }

    

    

}
