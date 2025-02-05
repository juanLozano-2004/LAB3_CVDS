package edu.eci.cvds.tdd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import edu.eci.cvds.tdd.library.*;

import java.util.Date;

public class LibraryTest {

    private Library library;
    private User user;
    private Book book;
    private Loan loan;

    @Before
    public void setUp() {
        // Inicialización antes de cada prueba
        library = new Library();
        user = new User("user1", "John Doe");
        book = new Book("1234567890", "Test Book", "Test Author");
        loan = new Loan(user, book, new Date(), LoanStatus.ACTIVE);
    }

    @Test
    public void testAddBook() {
        // Agregar un libro nuevo
        assertTrue(library.addBook(book));  

        // Intentar agregar el mismo libro (debe incrementar la cantidad)
        assertTrue(library.addBook(book));

        // Verificar que el libro ha sido agregado y la cantidad es 2
        assertTrue(library.books.containsKey(book));
    }

    @Test
    public void testAddBookNull() {
        // Intentar agregar un libro nulo (debería devolver false)
        assertFalse(library.addBook(null));
    }

    @Test
    public void testLoanABook() {
        // Agregar un libro a la biblioteca
        library.addBook(book);
        library.addUser(user);

        // Crear un préstamo exitoso
        Loan newLoan = library.loanABook(user.getId(), book.getIsbn());
        assertNotNull(newLoan);
        assertEquals(LoanStatus.ACTIVE, newLoan.getStatus());

        // Verificar que la cantidad de libros haya disminuido
        assertTrue(library.books.get(book) == 0);
    }

    @Test
    public void testLoanABookBookNotAvailable() {
        // Intentar hacer un préstamo de un libro no disponible
        library.addUser(user);

        Loan newLoan = library.loanABook(user.getId(), "non-existent-isbn");
        assertNull(newLoan); 
    }

    @Test
    public void testLoanABookUserNotExist() {
        // Intentar hacer un préstamo con un usuario que no existe
        library.addBook(book);

        Loan newLoan = library.loanABook("invalid-user", book.getIsbn());
        assertNull(newLoan); 
    }

    @Test
    public void testLoanABookUserHasActiveLoan() {
        // Intentar hacer un préstamo de un libro si el usuario ya tiene uno activo
        library.addBook(book);
        library.addUser(user);
        library.loanABook(user.getId(), book.getIsbn());

        // Intentar prestar el mismo libro nuevamente
        Loan newLoan = library.loanABook(user.getId(), book.getIsbn());
        assertNull(newLoan); 
    }

    @Test
    public void testReturnLoan() {
        // Crear un préstamo y devolverlo
        library.addBook(book);
        library.addUser(user);
        Loan loan = library.loanABook(user.getId(), book.getIsbn());

        Loan returnedLoan = library.returnLoan(loan);
        assertNotNull(returnedLoan);
        assertEquals(LoanStatus.RETURNED, returnedLoan.getStatus());

        // Verificar que la cantidad de libros ha aumentado
        assertTrue(library.books.get(book) == 1);
    }

    @Test
    public void testReturnLoanNonExistentLoan() {
        // Intentar devolver un préstamo que no existe
        Loan nonExistentLoan = new Loan(user, book, new Date(), LoanStatus.ACTIVE);
        Loan returnedLoan = library.returnLoan(nonExistentLoan);
        assertNull(returnedLoan); 
    }

    @Test
    public void testAddUser() {
        // Agregar un usuario
        assertTrue(library.addUser(user)); 
    }
}
