package data.model;

import java.io.Serializable;

public class Author extends Person implements Serializable {
    private static final long serialVersionUID = 7508481940058530471L;

    private final String credentials;

    private final String biography;

    public Author(String credentials, String biography, String firstName, String lastName, Address address, String telephone) {
        super(firstName, lastName, address, telephone);
        this.credentials = credentials;
        this.biography = biography;
    }
}
