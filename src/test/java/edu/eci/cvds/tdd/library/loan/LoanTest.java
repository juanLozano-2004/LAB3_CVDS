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
    void testSetAndGetLoanDate() {
        Loan loan = new Loan();
        LocalDateTime loanDate = LocalDateTime.now();
        loan.setLoanDate(loanDate);
        assertEquals(loanDate, loan.getLoanDate(), "La fecha del préstamo debe ser la asignada");
    }

    @Test
    void testSetAndGetReturnDate() {
        Loan loan = new Loan();
        LocalDateTime returnDate = LocalDateTime.now().plusDays(7);
        loan.setReturnDate(returnDate);
        assertEquals(returnDate, loan.getReturnDate(), "La fecha de devolución debe ser la asignada");
    }

    @Test
    void testSetAndGetStatus() {
        Loan loan = new Loan();
        loan.setStatus(LoanStatus.ACTIVE);
        assertEquals(LoanStatus.ACTIVE, loan.getStatus(), "El estado del préstamo debe ser ACTIVE");

        loan.setStatus(LoanStatus.RETURNED);
        assertEquals(LoanStatus.RETURNED, loan.getStatus(), "El estado del préstamo debe ser RETURNED");
    }

    @Test
    void testChangeLoanStatus() {
        Loan loan = new Loan();
        loan.setStatus(LoanStatus.ACTIVE);
        assertEquals(LoanStatus.ACTIVE, loan.getStatus(), "El estado del préstamo debe ser ACTIVE antes de cambiar");

        loan.setStatus(LoanStatus.RETURNED);
        assertEquals(LoanStatus.RETURNED, loan.getStatus(), "El estado del préstamo debe cambiar a RETURNED");
    }

    @Test
    void testLoanWithoutSettingReturnDate() {
        Loan loan = new Loan();
        assertNull(loan.getReturnDate(), "La fecha de devolución debe ser nula si no se ha asignado");
    }

    @Test
    void testLoanWithoutSettingStatus() {
        Loan loan = new Loan();
        assertNull(loan.getStatus(), "El estado del préstamo debe ser nulo si no se ha asignado");
    }

    @Test
    void testLoanWithoutSettingLoanDate() {
        Loan loan = new Loan();
        assertNull(loan.getLoanDate(), "La fecha del préstamo debe ser nula si no se ha asignado");
    }
}
