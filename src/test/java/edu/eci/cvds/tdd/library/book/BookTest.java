package edu.eci.cvds.tdd.library.book;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    
    @Test
    void testBookCreation() {
        Book book = new Book("Effective Java", "Joshua Bloch", "978-0134685991");
        assertEquals("Effective Java", book.getTittle());
        assertEquals("Joshua Bloch", book.getAuthor());
        assertEquals("978-0134685991", book.getIsbn());
    }

    @Test
    void testBookEquality() {
        Book book1 = new Book("Effective Java", "Joshua Bloch", "978-0134685991");
        Book book2 = new Book("Effective Java", "Joshua Bloch", "978-0134685991");
        assertEquals(book1, book2, "Los libros con el mismo ISBN deben ser iguales");
    }
}