package data.model;

import java.io.Serializable;

public abstract class Person implements Serializable {
    private static final long serialVersionUID = 3665880920647848288L;

    private String firstName;

    private String lastName;

    private Address address;

    private String telephone;

    public Person(String firstName, String lastName, Address address, String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
