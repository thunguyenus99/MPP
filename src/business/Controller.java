package business;

import business.exception.AddBookCopyException;
import business.exception.CheckoutException;
import business.exception.GetCheckoutRecordException;
import business.exception.LoginException;
import data.model.*;
import data.repository.LibraryRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Controller {

    private static Controller INSTANCE;
    private final LibraryRepository dataRepository;

    private Controller() {
        this.dataRepository = new LibraryRepositoryImpl();
    }

    public static Controller getInstance() {
        if (Controller.INSTANCE == null) {
            Controller.INSTANCE = new Controller();
        }
        return Controller.INSTANCE;
    }

    public User login(String userId, String password) throws LoginException {
        User user = dataRepository.getUserById(userId);
        if (user == null) {
            throw new LoginException("User does not exist.");
        }
        if (!user.getPassword().equals(password)) {
            throw new LoginException("Password is incorrect");
        }
        return user;
    }

    public CheckoutRecord checkoutRecord(String memberId, String isbn) throws CheckoutException {
        LibraryMember member = dataRepository.getMemberById(memberId);
        if (member == null) {
            throw new CheckoutException("Member does not exist.");
        }

        Book book = dataRepository.getBookByIsbn(isbn);
        if (book == null) {
            throw new CheckoutException("Book does not exist.");
        }

        Optional<BookCopy> optionalBookCopy = book.getBookCopyList().stream()
                .filter(BookCopy::isAvailable)
                .findFirst();
        if (optionalBookCopy.isEmpty()) {
            throw new CheckoutException("Book is not available.");
        }
        BookCopy bookCopy = optionalBookCopy.get();

        LocalDate checkoutDate = LocalDate.now();
        LocalDate dueDate = checkoutDate.plusDays(book.getMaxCheckoutLength());
        CheckoutRecord checkoutRecord = new CheckoutRecord(
                UUID.randomUUID().toString(),
                bookCopy,
                member,
                checkoutDate,
                dueDate,
                null,
                0,
                null
        );

        bookCopy.setCheckoutRecord(checkoutRecord);
        book.getBookCopyList().set(bookCopy.getCopyNum(), bookCopy);

        member.addCheckoutRecord(checkoutRecord);

        dataRepository.saveMember(member);
        dataRepository.saveCheckoutRecord(checkoutRecord);
        dataRepository.saveBook(book);

        return checkoutRecord;
    }

    public void addMember(LibraryMember member) {
        dataRepository.saveMember(member);
    }

    public void addBookCopy(String isbn) throws AddBookCopyException {
        Book book = dataRepository.getBookByIsbn(isbn);
        if (book == null) {
            throw new AddBookCopyException("Book does not exist.");
        }
        book.addBookCopy();
    }

    public void addBook(Book book) {
        dataRepository.saveBook(book);
    }

    public List<CheckoutRecord> getCheckoutRecordByMemberId(String memberId) throws GetCheckoutRecordException {
        LibraryMember member = dataRepository.getMemberById(memberId);
        if (member == null) {
            throw new GetCheckoutRecordException("Member does not exist.");
        }
        return member.getCheckoutRecordList();
    }

    public Book getBookByIsbn(String isbn) {
        return dataRepository.getBookByIsbn(isbn);
    }

    public CheckoutRecord checkoutBook(String memberId, String isbn) throws CheckoutException {
        LibraryMember libraryMember = dataRepository.getMemberById(memberId);
        if (libraryMember == null) {
            throw new CheckoutException("Library member not found!");
        }
        Book book = dataRepository.getBookByIsbn(isbn);
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
        CheckoutRecord checkoutRecord = new CheckoutRecord(
                UUID.randomUUID().toString(),
                bookCopy,
                libraryMember,
                today,
                today.plusDays(book.getMaxCheckoutLength()),
                null,
                0,
                null
        );
        libraryMember.addCheckoutRecord(checkoutRecord);
        dataRepository.saveMember(libraryMember);
        dataRepository.saveBook(book);
        return checkoutRecord;
    }
}
