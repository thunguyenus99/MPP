package data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {
    private static final long serialVersionUID = 6110690276685962829L;

    private String isbn;

    private String title;

    private int maxCheckoutLength;

    private List<BookCopy> bookCopyList;

    private List<Author> authorList;

    public Book(String isbn, String title, int maxCheckoutLength, int numOfCopies, List<Author> authorList) {
        this.isbn = isbn;
        this.title = title;
        this.maxCheckoutLength = maxCheckoutLength;
        this.authorList = authorList;
        this.bookCopyList = new ArrayList<>();
        for (int i = 0; i < numOfCopies; i++) {
            addBookCopy();
        }
    }

    public String getIsbn() {
        return isbn;
    }

    public List<BookCopy> getBookCopyList() {
        return bookCopyList;
    }

    public int getMaxCheckoutLength() {
        return maxCheckoutLength;
    }

    public void addBookCopy() {
        bookCopyList.add(new BookCopy(this, this.bookCopyList.size(), true));
    }
}
