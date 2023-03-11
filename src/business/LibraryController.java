package business;

import business.exception.*;
import business.model.DataStatus;
import data.model.*;
import data.repository.LibraryRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LibraryController {

    private static LibraryController INSTANCE;
    private final LibraryRepository repository;

    private LibraryController() {
        this.repository = new LibraryRepositoryImpl();
    }

    public static LibraryController getInstance() {
        if (LibraryController.INSTANCE == null) {
            LibraryController.INSTANCE = new LibraryController();
        }
        return LibraryController.INSTANCE;
    }

    public User login(String userId, String password) throws LoginException {
        User user = repository.getUserById(userId);
        if (user == null) {
            throw new LoginException("User does not exist.");
        }
        if (!user.getPassword().equals(password)) {
            throw new LoginException("Password is incorrect");
        }
        return user;
    }

    public DataStatus addMember(LibraryMember member) {
        LibraryMember existedMember = repository.getMemberById(member.getMemberId());
        DataStatus status = existedMember == null ? DataStatus.ADD : DataStatus.UPDATE;
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

    public DataStatus addBook(Book book){
        Book existedBook = repository.getBookByIsbn(book.getIsbn());
        DataStatus status = existedBook == null ? DataStatus.ADD : DataStatus.UPDATE;
        repository.saveBook(book);
        return status;
    }

    public List<CheckoutRecord> getCheckoutRecordByMemberId(String memberId) throws GetCheckoutRecordException {
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
        CheckoutRecord checkoutRecord = new CheckoutRecord(UUID.randomUUID().toString(), bookCopy, libraryMember, today, today.plusDays(book.getMaxCheckoutLength()), null, 0, null);
        libraryMember.addCheckoutRecord(checkoutRecord);
        bookCopy.setCheckoutRecord(checkoutRecord);
        repository.saveMember(libraryMember);
        repository.saveBook(book);
        return checkoutRecord;
    }
}
