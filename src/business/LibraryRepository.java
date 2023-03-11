package business;

import data.model.Book;
import data.model.LibraryMember;
import data.model.User;

public interface LibraryRepository {
    void saveMember(LibraryMember member);

    void saveBook(Book book);

    LibraryMember getMemberById(String memberId);

    Book getBookByIsbn(String isbn);

    User getUserById(String userId);
}
