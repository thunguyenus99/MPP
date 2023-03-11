package business;

import business.exception.AddBookCopyException;
import business.exception.CheckoutException;
import business.exception.GetCheckoutRecordException;
import business.exception.LoginException;
import business.model.ModificationType;
import data.model.*;
import data.repository.LibraryRepositoryImpl;
import presentation.LoggedInUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class LibraryController {

    private static LibraryController instance;
    private final LibraryRepository repository;

    private LibraryController() {
        this.repository = new LibraryRepositoryImpl();
    }

    public static LibraryController getInstance() {
        if (LibraryController.instance == null) {
            LibraryController.instance = new LibraryController();
        }
        return LibraryController.instance;
    }

    public void login(String userId, String password) throws LoginException {
        User user = repository.getUserById(userId);
        if (user == null) {
            throw new LoginException("User does not exist.");
        }
        if (!user.getPassword().equals(password)) {
            throw new LoginException("Password is incorrect");
        }
        LoggedInUser.set(user);
    }

    public ModificationType addMember(LibraryMember member) {
        LibraryMember existedMember = repository.getMemberById(member.getMemberId());
        ModificationType status = existedMember == null ? ModificationType.ADD : ModificationType.UPDATE;
        repository.saveMember(member);
        return status;
    }

    public Book addBookCopy(String isbn) throws AddBookCopyException {
        Book book = repository.getBookByIsbn(isbn);
        if (book == null) {
            throw new AddBookCopyException("Book does not exist.");
        }
        book.addBookCopy();
        repository.saveBook(book);
        return book;
    }

    public ModificationType addBook(Book book){
        Book existedBook = repository.getBookByIsbn(book.getIsbn());
        ModificationType status = existedBook == null ? ModificationType.ADD : ModificationType.UPDATE;
        repository.saveBook(book);
        return status;
    }

    public List<CheckoutRecord> getCheckoutRecords(String memberId) throws GetCheckoutRecordException {
        LibraryMember member = repository.getMemberById(memberId);
        if (member == null) {
            throw new GetCheckoutRecordException("Member does not exist.");
        }
        return member.getCheckoutRecordList();
    }

    public Book getBookByIsbn(String isbn) {
        return repository.getBookByIsbn(isbn);
    }

    public CheckoutRecord checkoutBook(String memberId, String isbn) throws CheckoutException {
        LibraryMember libraryMember = repository.getMemberById(memberId);
        if (libraryMember == null) {
            throw new CheckoutException("Library member not found!");
        }
        Book book = repository.getBookByIsbn(isbn);
        if (book == null) {
            throw new CheckoutException("Book not found!");
        }
        Optional<BookCopy> bookCopyOptional = book.getNextAvailableBookCopy();
        if (bookCopyOptional.isEmpty()) {
            throw new CheckoutException("Book copy not available!");
        }
        BookCopy bookCopy = bookCopyOptional.get();
        bookCopy.setAvailable(false);
        LocalDate today = LocalDate.now();
        CheckoutRecord checkoutRecord = new CheckoutRecord(bookCopy, libraryMember, today, today.plusDays(book.getMaxCheckoutLength()), null, 0, null);
        libraryMember.addCheckoutRecord(checkoutRecord);
        bookCopy.setCheckoutRecord(checkoutRecord);
        repository.saveMember(libraryMember);
        repository.saveBook(book);
        return checkoutRecord;
    }
}
