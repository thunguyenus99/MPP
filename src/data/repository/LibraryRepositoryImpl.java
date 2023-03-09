package data.repository;

import data.model.Book;
import data.model.CheckoutRecord;
import data.model.LibraryMember;
import data.model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class LibraryRepositoryImpl implements LibraryRepository {
    enum StorageType {
        BOOK, MEMBER, USER, CHECKOUT_RECORD;
    }

    public static final String OUTPUT_DIR = System.getProperty("user.dir")
            + "/src/data.repository/storage";

    // get all data
    @Override
    public HashMap<String, User> readUsers() {
        return (HashMap<String, User>) readFromStorage(StorageType.USER);
    }

    @Override
    public HashMap<String, LibraryMember> readMembers() {
        return (HashMap<String, LibraryMember>) readFromStorage(StorageType.MEMBER);
    }

    @Override
    public HashMap<String, Book> readBooks() {
        return (HashMap<String, Book>) readFromStorage(StorageType.BOOK);
    }

    @Override
    public HashMap<String, CheckoutRecord> readCheckoutRecords() {
        return (HashMap<String, CheckoutRecord>) readFromStorage(StorageType.CHECKOUT_RECORD);
    }

    // get data by id
    public User getUserById(String useId) {
        HashMap<String, User> userMap = readUsers();
        return userMap.get(useId);
    }

    public LibraryMember getMemberById(String memberId) {
        HashMap<String, LibraryMember> memberMap = readMembers();
        return memberMap.get(memberId);
    }

    public Book getBookByIsbn(String isbn) {
        HashMap<String, Book> bookMap = readBooks();
        return bookMap.get(isbn);
    }


    // save data
    @Override
    public void saveUser(User user) {
        HashMap<String, User> map = readUsers();
        map.put(user.getUserId(), user);
        saveToStorage(StorageType.USER, map);
    }

    @Override
    public void saveMember(LibraryMember member) {
        HashMap<String, LibraryMember> map = readMembers();
        map.put(member.getMemberId(), member);
        saveToStorage(StorageType.MEMBER, map);
    }

    @Override
    public void saveBook(Book book) {
        HashMap<String, Book> map = readBooks();
        map.put(book.getIsbn(), book);
        saveToStorage(StorageType.BOOK, map);
    }

    @Override
    public void saveCheckoutRecord(CheckoutRecord checkoutRecord) {
        HashMap<String, CheckoutRecord> map = readCheckoutRecords();
        map.put(checkoutRecord.getRecordId(), checkoutRecord);
        saveToStorage(StorageType.CHECKOUT_RECORD, map);
    }

    // initialize data
    public static void loadBookMap(List<Book> bookList) {
        HashMap<String, Book> bookMap = new HashMap<>();
        bookList.forEach(book -> bookMap.put(book.getIsbn(), book));
        saveToStorage(StorageType.BOOK, bookMap);
    }

    public static void loadUserMap(List<User> userList) {
        HashMap<String, User> userMap = new HashMap<>();
        userList.forEach(user -> userMap.put(user.getUserId(), user));
        saveToStorage(StorageType.USER, userMap);
    }

    public static void loadMemberMap(List<LibraryMember> memberList) {
        HashMap<String, LibraryMember> memberMap = new HashMap<>();
        memberList.forEach(member -> memberMap.put(member.getMemberId(), member));
        saveToStorage(StorageType.MEMBER, memberMap);
    }

    // write to file
    private static void saveToStorage(StorageType type, Object ob) {
        ObjectOutputStream out = null;
        try {
            Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
            out = new ObjectOutputStream(Files.newOutputStream(path));
            out.writeObject(ob);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                }
            }
        }
    }

    // read from file
    private static Object readFromStorage(StorageType type) {
        ObjectInputStream in = null;
        Object retVal = null;
        try {
            Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
            in = new ObjectInputStream(Files.newInputStream(path));
            retVal = in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                }
            }
        }
        return retVal;
    }
}
