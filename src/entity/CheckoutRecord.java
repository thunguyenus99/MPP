package entity;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecord implements Serializable {
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
}
