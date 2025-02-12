package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;
import java.time.LocalDateTime;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryTest {
    private Library library;

    @BeforeEach
    void initializeLibrary() {
        library = new Library();
    }

    @Test
    void testLoanCreationWhenBookIsAvailable() {
        Book book = new Book("El Alquimista", "Paulo Coelho", "ABC123");
        User user = new User();
        user.setId("userX");
        user.setName("Pedro Gómez");

        library.addUser(user);
        library.addBook(book);

        Loan loan = library.loanABook(user.getId(), book.getIsbn());

        assertNotNull(loan);
        assertEquals(LoanStatus.ACTIVE, loan.getStatus());
        assertEquals(user.getId(), loan.getUser().getId());
        assertEquals(book.getIsbn(), loan.getBook().getIsbn());
        assertNotNull(loan.getLoanDate());
        assertEquals(0, library.getBooks().get(book));
    }

    @Test
    void testLoanFailsWhenBookIsUnavailable() {
        User user = new User();
        user.setId("userY");
        user.setName("Ana Martínez");

        library.addUser(user);

        assertThrows(IllegalArgumentException.class, () -> library.loanABook(user.getId(), "XYZ999"));
    }

    @Test
    void testLoanFailsForNonExistingUser() {
        Book book = new Book("Rebelión en la Granja", "George Orwell", "GEO456");
        library.addBook(book);

        assertThrows(IllegalArgumentException.class, () -> library.loanABook("unknown_user", book.getIsbn()));
    }

    @Test
    void testPreventUserFromBorrowingSameBookTwice() {
        Book book = new Book("El Principito", "Antoine de Saint-Exupéry", "PRIN789");
        User user = new User();
        user.setId("userZ");
        user.setName("Carlos Herrera");

        library.addUser(user);
        library.addBook(book);

        library.loanABook(user.getId(), book.getIsbn());

        assertThrows(IllegalArgumentException.class, () -> library.loanABook(user.getId(), book.getIsbn()));
    }

    @Test
    void testLoanWithMultipleCopiesAvailable() {
        Book book = new Book("Cien años de soledad", "Gabriel García Márquez", "SOL654");
        User user1 = new User();
        user1.setId("userA");
        user1.setName("Laura Pérez");

        User user2 = new User();
        user2.setId("userB");
        user2.setName("Fernando Díaz");

        library.addUser(user1);
        library.addUser(user2);

        library.addBook(book);
        library.addBook(book);

        Loan loan1 = library.loanABook(user1.getId(), book.getIsbn());
        Loan loan2 = library.loanABook(user2.getId(), book.getIsbn());

        assertNotNull(loan1);
        assertNotNull(loan2);
        assertEquals(LoanStatus.ACTIVE, loan1.getStatus());
        assertEquals(LoanStatus.ACTIVE, loan2.getStatus());
        assertEquals(0, library.getBooks().get(book));
    }

    @Test
    void testAddingNewBookToLibrary() {
        Book book = new Book("Fundamentos de Física", "David Halliday", "PHY789");
        assertTrue(library.addBook(book));
    }

    @Test
    void testAddingNullBookThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> library.addBook(null));
    }

    @Test
    void testAddingExistingBookIncreasesCount() {
        Book book = new Book("Moby Dick", "Herman Melville", "MD321");
        library.addBook(book);
        library.addBook(book);

        assertEquals(2, library.getBooks().get(book));
    }



    @Test
    void testSuccessfulLoanReturn() {
        Book book = new Book("Los Miserables", "Victor Hugo", "MIS258");
        User user = new User();
        user.setId("userD");
        user.setName("Mariana López");
        library.addUser(user);
        library.addBook(book);
        library.addBook(book);

        Loan loan = library.loanABook(user.getId(), book.getIsbn());
        Loan returnedLoan = library.returnLoan(loan);

        assertNotNull(returnedLoan);
        assertEquals(LoanStatus.RETURNED, returnedLoan.getStatus());
        assertEquals(user.getId(), returnedLoan.getUser().getId());
        assertEquals(book.getIsbn(), returnedLoan.getBook().getIsbn());
        assertNotNull(returnedLoan.getReturnDate());
        assertEquals(2, library.getBooks().get(book));
    }
}
