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

        Book book = bookRepository.findById(id); //Se crea variable para llamar una sola vez a la funcion

        if(book == null){
            throw new BookNotFoundException("No se encontró el libro con el siguiente Id: " + id);
        }

        return book;

    }

    public void addUser(String id, String name){

        if(users.stream().anyMatch(u -> u.getId().equals(id))){ //Se valida si el id ya existe dentro de la lista de usuarios
            System.out.println("Ya existe un usuario con el id: "+ id);
            return;
        }

        users.add(new User(id, name));
    }

    
    //Getter para obtener usuarios
    public List<User> getUsers() {
        return users;
    }


    public void borrowBook(String userId, String bookId) {
    try {
        // Buscar usuario por ID
        User user = users.stream()
                        .filter(u -> u.getId().equals(userId))
                        .findFirst()
                        .orElseThrow(() -> new UserNotFounfException("No se encontró el usuario con el siguiente Id: " + userId));

        // Buscar libro por ID
        Book book = bookRepository.findById(bookId);

        if (book == null) {
            throw new BookNotFoundException("No se encontró el libro con el siguiente Id: " + bookId);
        }

        // Verificar si el libro ya está prestado
        if (book.isBorrowed()) {
            throw new IllegalStateException("El libro ya ha sido prestado");
        }

        // Registrar préstamo
        loanRepository.saveLoan(new Loan(user, book));
        book.setBorrowed(true);
        System.out.println("Préstamo realizado con éxito.");

        //Excepciones

    } catch (UserNotFounfException | BookNotFoundException e) {
        System.err.println("Error: " + e.getMessage());
    } 
}


    public Loan getLoanByUserId(String userid){

        Loan loan = loanRepository.findById(userid);

        if(loan == null){
            throw new UserNotFounfException("No se encontró el usuario con el siguiente Id: " + userid);
        }

        return loan;
    }
        
        





}
