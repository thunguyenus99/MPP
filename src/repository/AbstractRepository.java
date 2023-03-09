package repository;

import entity.*;

import java.util.HashMap;

public interface AbstractRepository {
    HashMap<String, User> readUsers();
    HashMap<String, LibraryMember> readMembers();
    HashMap<String, Book> readBooks();
    HashMap<String, CheckoutRecord> readCheckoutRecords();
    void saveUser(User user);
    void saveMember(LibraryMember member);
    void saveBook(Book book);
    void saveCheckoutRecord(CheckoutRecord checkoutRecord);
}
