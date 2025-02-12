package edu.eci.cvds.tdd.library.loan;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class LoanTest {
    
    @Test
    void testLoanCreation() {
        Loan loan = new Loan();
        assertNotNull(loan, "El préstamo debe ser creado correctamente");
    }

    @Test
    void testLoanStatus() {
        Loan loan = new Loan();
        loan.setStatus(LoanStatus.ACTIVE);
        assertEquals(LoanStatus.ACTIVE, loan.getStatus(), "El estado del préstamo debe ser ACTIVE");
    }

    @Test
    void testLoanReturnDate() {
        Loan loan = new Loan();
        LocalDateTime returnDate = LocalDateTime.now();
        loan.setReturnDate(returnDate);
        assertEquals(returnDate, loan.getReturnDate(), "La fecha de devolución debe ser la asignada");
    }
}