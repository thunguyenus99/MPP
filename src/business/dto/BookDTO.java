package business.dto;

import java.util.List;

public class BookDTO {
    private String isbn;

    private String title;

    private int maxCheckoutLength;

    private int numOfCopies;

    private List<AuthorDTO> authorDTOList;

    public BookDTO(String isbn, String title, int maxCheckoutLength, int numOfCopies, List<AuthorDTO> authorDTOList) {
        this.isbn = isbn;
        this.title = title;
        this.maxCheckoutLength = maxCheckoutLength;
        this.numOfCopies = numOfCopies;
        this.authorDTOList = authorDTOList;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxCheckoutLength() {
        return maxCheckoutLength;
    }

    public int getNumOfCopies() {
        return numOfCopies;
    }

    public List<AuthorDTO> getAuthorDTOList() {
        return authorDTOList;
    }
}
