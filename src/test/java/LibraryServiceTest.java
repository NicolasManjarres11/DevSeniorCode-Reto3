
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

import com.devsenior.nmanja.controller.LibraryService;
import com.devsenior.nmanja.exceptions.BookNotFoundException;
import com.devsenior.nmanja.interfaces.BookRepository;
import com.devsenior.nmanja.interfaces.LoanRepository;
import com.devsenior.nmanja.model.Book;
import com.devsenior.nmanja.model.Loan;
import com.devsenior.nmanja.model.User;

public class LibraryServiceTest {

    @Mock
    private BookRepository bookRepository; //
    @Mock
    private LoanRepository loanRepository; //

    private LibraryService libraryService;

    @BeforeEach

    public void setup() {
        MockitoAnnotations.openMocks(this); //Se instancia los mocks inicializados
        libraryService = new LibraryService(bookRepository, loanRepository);
    }

    @Test

    void testAddBook() {

        var id = "1";
        var title = "Libro 1";
        var author = "Autor 1";

        libraryService.addBook(id, title, author);

        //Se verifica que se haya llamado al metodo y que si agrega un objeto de tipo libro

        verify(bookRepository).saveBook(any(Book.class)); 

    }

    @Test

    void testGetBookById() {

        var mockBook = new Book("111", "El principito", "Antoine de Saint-Exupery");

        Mockito.when(bookRepository.findById("111")).thenReturn(mockBook); //Se mockea el metodo

        Book book = libraryService.getBookById("111"); //Se llama al metodo

        assertNotNull(book, "No se encontró el libro");

        assertEquals(mockBook, book);
        assertEquals("111", book.getId());
        assertEquals("El principito", book.getTitle());
        assertEquals("Antoine de Saint-Exupery", book.getAuthor());

    }

    @Test

    void testGetBookByIdWhenBookNotFound() {

        //Se mockea el metodo, para simular un libro no existente

        Mockito.when(bookRepository.findById("123")).thenReturn(null); 

        //Se agrega expresion lambda supplier
        //Se guarda en variable el valor retornado por el metodo
        var exception = assertThrows(BookNotFoundException.class, () -> libraryService.getBookById("123"));

        //Se valida si el libro es nulo
        assertNull(bookRepository.findById("123"));

        //Se valida si el mensaje es el correcto
        assertEquals("No se encontró el libro con el siguiente Id: 123", exception.getMessage());

    }

    @Test

    void addUser(){

        
        var users = libraryService.getUsers();

        //Se llama al metodo para agregar un usuario

        libraryService.addUser("125403", "Nicolas Manjarres");

        //Se valida si se agregó un usuario a la lista

        assertEquals(1, users.size()); 

        //Se valida si el nombre  es correcto

        assertEquals("Nicolas Manjarres", users.get(0).getName()); 

        //Se revisa que no sea nulo

        assertNotNull(users);

    }

    @Test
    void testBorrowBook(){

        

        var userMock = new User("18920", "Nicolas Manjarres");
        var bookMock = new Book("123", "El principito", "Antoine de Saint-Exupery");
        var loanMock = new Loan(userMock, bookMock);

        libraryService.addUser(userMock.getId(), userMock.getName());
        libraryService.addBook(bookMock.getId(), bookMock.getTitle(), bookMock.getAuthor());

        Mockito.when(loanRepository.findById("18920")).thenReturn(loanMock);
        Mockito.when(bookRepository.findById("123")).thenReturn(bookMock);

        libraryService.borrowBook("18920", "123");

        assertTrue(bookMock.isBorrowed());

    }
    @Test
    void testBorrowBookWhnIsAllowed(){

        var userMock = new User("18920", "Nicolas Manjarres");
        var bookMock = new Book("123", "El principito", "Antoine de Saint-Exupery");
        
        //Se presta el libro
        bookMock.setBorrowed(true);
        libraryService.addUser(userMock.getId(), userMock.getName());
        Mockito.when(bookRepository.findById("123")).thenReturn(bookMock);

        //Se agrega expresion lambda supplier
        //Se guarda en variable el valor retornado por el metodo
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            libraryService.borrowBook("18920", "123");
        });

        assertTrue(bookMock.isBorrowed());
        assertTrue(exception.getMessage().contains("El libro ya ha sido prestado"));
    }

    @Test
    void testFindLoanById(){

        var userMock = new User("18920", "Nicolas Manjarres");
        var bookMock = new Book("123", "El principito", "Antoine de Saint-Exupery");
        var loanMock = new Loan(userMock, bookMock);

        Mockito.when(loanRepository.findById("18920")).thenReturn(loanMock);

        assertEquals(loanMock, libraryService.getLoanByUserId("18920"));
        assertEquals("18920", loanMock.getUser().getId());
        assertEquals("123", loanMock.getBook().getId());

    }



    }

