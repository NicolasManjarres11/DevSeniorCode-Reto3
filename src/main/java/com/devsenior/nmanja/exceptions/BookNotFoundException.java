package com.devsenior.nmanja.exceptions;

import java.util.NoSuchElementException;

public class BookNotFoundException extends NoSuchElementException{
    
    public BookNotFoundException(String message){
        super(message);
    }

}
