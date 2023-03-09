package entity;

import java.io.Serializable;

public class Author extends Person implements Serializable {
    private static final long serialVersionUID = 7508481940058530471L;

    private String confidentials;

    private String biography;

    public Author(String confidentials, String biography, String firstName, String lastName, Address address, String telephone) {
        super(firstName, lastName, address, telephone);
        this.confidentials = confidentials;
        this.biography = biography;
    }
}
