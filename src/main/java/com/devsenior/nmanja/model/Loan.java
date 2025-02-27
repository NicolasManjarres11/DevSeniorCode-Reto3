package com.devsenior.nmanja.model;

import java.util.Date;

public class Loan {

    private Book book;
    private User user;
    private Date loanDate;


    public Loan(Book book, User user) {
        this.book = book;
        this.user = user;
        this.loanDate = new Date();
    }



    

}
