import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.devsenior.nmanja.controller.LibraryService;
import com.devsenior.nmanja.interfaces.BookRepository;
import com.devsenior.nmanja.interfaces.LoanRepository;

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
        
    }

    



}
