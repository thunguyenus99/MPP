package repository.initTestData;

import entity.*;
import repository.DataRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InitTestData {
    private static List<Address> addresses = new ArrayList<>() {
        {
            add(new Address("101 S. Main", "Fairfield", "IA", "52556"));
            add(new Address("51 S. George", "Georgetown", "MI", "65434"));
            add(new Address("23 Headley Ave", "Seville", "Georgia", "41234"));
            add(new Address("1 N. Baton", "Baton Rouge", "LA", "33556"));
            add(new Address("5001 Venice Dr.", "Los Angeles", "CA", "93736"));
            add(new Address("1435 Channing Ave", "Palo Alto", "CA", "94301"));
            add(new Address("42 Dogwood Dr.", "Fairfield", "IA", "52556"));
            add(new Address("501 Central", "Mountain View", "CA", "94707"));
        }
    };

    private static List<LibraryMember> members = new ArrayList<>() {
        {
            add(new LibraryMember("1001", "Andy", "Rogers",  addresses.get(4), "641-223-2211"));
            add(new LibraryMember("1002", "Drew", "Stevens",  addresses.get(5), "702-998-2414"));
            add(new LibraryMember("1003", "Sarah", "Eagleton", addresses.get(6), "451-234-8811"));
            add(new LibraryMember("1004", "Ricardo", "Montalbahn", addresses.get(7), "641-472-2871"));
        }
    };

    private static List<Author> authors = new ArrayList<>() {
        {
            add(new Author("confidentials", "A happy man is he.", "Joe", "Thomas", addresses.get(0), "641-445-2123"));
            add(new Author("confidentials", "A happy wife is she.", "Sandra", "Thomas", addresses.get(0), "641-445-2123"));
            add(new Author("confidentials", "Thinker of thoughts.", "Nirmal", "Pugh", addresses.get(1), "641-919-3223"));
            add(new Author("confidentials", "Author of childrens' books.", "Andrew", "Cleveland", addresses.get(2), "976-445-2232"));
            add(new Author("confidentials", "Known for her clever style.", "Sarah", "Connor", addresses.get(3), "123-422-2663"));
        }
    };

    private static List<Book> books = new ArrayList<>() {
        {
            add(new Book("23-11451", "The Big Fish", 21, 2, List.of(authors.get(0), authors.get(1))));
            add(new Book("28-12331", "Antartica", 7, 2, Collections.singletonList(authors.get(2))));
            add(new Book("99-22223", "Thinking Java", 21, 2 , Collections.singletonList(authors.get(3))));
            add(new Book("48-56882", "Jimmy's First Day of School", 7, 2 , Collections.singletonList(authors.get(4))));
        };
    };

    private static List<User> users = new ArrayList<>() {
        {
            add(new User("101", "xyz", List.of(Role.LIBRARIAN)));
            add(new User("102", "abc", List.of(Role.ADMIN)));
            add(new User("103", "111", List.of(Role.LIBRARIAN, Role.ADMIN)));
        }
    };

    public static void main(String[] args) {
        DataRepository.loadBookMap(books);
        DataRepository.loadUserMap(users);
        DataRepository.loadMemberMap(members);
    }
}
