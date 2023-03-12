package business.dto;

public class AuthorDTO {
    private String credentials;

    private String biography;

    private String firstName;

    private String lastName;

    private String telephone;

    private AddressDTO addressDTO;

    public AuthorDTO(String credentials, String biography, String firstName, String lastName, String telephone, AddressDTO addressDTO) {
        this.credentials = credentials;
        this.biography = biography;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.addressDTO = addressDTO;
    }

    public String getCredentials() {
        return credentials;
    }

    public String getBiography() {
        return biography;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }
}
