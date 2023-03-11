package business.mapper;

import data.model.Book;
import business.dto.BookDTO;

public class BookDTOMapper implements Mapper<BookDTO, Book> {
    @Override
    public Book map(BookDTO from) {
        return new Book(
                from.getIsbn(),
                from.getTitle(),
                from.getMaxCheckoutLength(),
                from.getNumOfCopies(),
                new AuthorDTOMapper().mapList(from.getAuthorDTOList())
        );
    }
}
