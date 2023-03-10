package data.model;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecord implements Serializable {
    private static final long serialVersionUID = -5073948164675636570L;
    private String recordId;

    private BookCopy bookCopy;

    private LibraryMember libraryMember;

    private LocalDate checkoutDate;

    private LocalDate dueDate;

    private LocalDate checkinDate;

    private double fine;

    private LocalDate paidFineDate;

    public CheckoutRecord(String recordId, BookCopy bookCopy, LibraryMember libraryMember, LocalDate checkoutDate, LocalDate dueDate, LocalDate checkinDate, double fine, LocalDate paidFineDate) {
        this.recordId = recordId;
        this.bookCopy = bookCopy;
        this.libraryMember = libraryMember;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.checkinDate = checkinDate;
        this.fine = fine;
        this.paidFineDate = paidFineDate;
    }

    public String getRecordId() {
        return recordId;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public LibraryMember getLibraryMember() {
        return libraryMember;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public double getFine() {
        return fine;
    }

    public LocalDate getPaidFineDate() {
        return paidFineDate;
    }
}
