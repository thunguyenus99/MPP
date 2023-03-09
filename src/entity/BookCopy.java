package entity;

import java.io.Serializable;

public class BookCopy implements Serializable {
    private static final long serialVersionUID = -63976228084869815L;

    private Book book;

    private int copyNum;

    private boolean available;

    private CheckoutRecord checkoutRecord;

    public BookCopy(Book book, int copyNum, boolean available) {
        this.book = book;
        this.copyNum = copyNum;
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getCopyNum() {
        return copyNum;
    }

    public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
        this.checkoutRecord = checkoutRecord;
    }
}
