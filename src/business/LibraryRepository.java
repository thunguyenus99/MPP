package business;

import data.model.Book;
import data.model.CheckoutRecord;
import data.model.LibraryMember;
import data.model.User;

import java.util.HashMap;

public interface LibraryRepository {
    HashMap<String, User> readUsers();

    HashMap<String, LibraryMember> readMembers();

    HashMap<String, Book> readBooks();

    HashMap<String, CheckoutRecord> readCheckoutRecords();

    void saveUser(User user);

    void saveMember(LibraryMember member);

    void saveBook(Book book);

    void saveCheckoutRecord(CheckoutRecord checkoutRecord);

    LibraryMember getMemberById(String memberId);

    Book getBookByIsbn(String isbn);

    User getUserById(String userId);
}
