import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

public class LibraryServiceTest {

    @Mock private BookRepository bookRepository; //
    @Mock private LoanRepository loanRepository; //

    private LibraryService libraryService;

    @BeforeEach

    public void setup(){
        MockitoAnnotations.openMocks(this); //Se instancia los mocks inicializados
        libraryService = new LibraryService(bookRepository, loanRepository);
    }

    @Test

    void testAddBook() {

        var id = "1";
        var title = "Libro 1";
        var author = "Autor 1";

        

        libraryService.addBook(id, title, author);

        verify(bookRepository).saveBook(any(Book.class)); //Se verifica que se haya llamado al metodo y que si agrega un objeto de tipo libro

        

        
    }


    @Test

    void testGetBookById() {

        var mockBook = new Book("111","El principito","Antoine de Saint-Exupery");

        Mockito.when(bookRepository.findById("111")).thenReturn(mockBook); //Se mockea el metodo
        

        Book book = libraryService.getBookById("111"); //Se llama al metodo

        assertNotNull(book,"No se encontró el libro");

        assertEquals(mockBook, book);
        assertEquals("111", book.getId());
        assertEquals("El principito", book.getTitle());
        assertEquals("Antoine de Saint-Exupery", book.getAuthor());

    }

    @Test

    void testGetBookByIdWhenBookNotFound() {

        Mockito.when(bookRepository.findById("123")).thenReturn(null); //Se mockea el metodo, para simular un libro no existente

        //Se agrega expresion lambda supplier
        //Se guarda en variable el valor retornado por el metodo


        var exception = assertThrows(BookNotFoundException.class, () -> libraryService.getBookById("123"));
        

        //Se valida si el libro es nulo
         
        assertNull(bookRepository.findById("123"));

        //Se valida si el mensaje es el correcto
        assertEquals("No se encontró el libro con el siguiente Id: 123", exception.getMessage()); 

    }



}
