package presentation.dto;

public class LibraryMemberDTO {
    private String memberId;

    private String firstName;

    private String lastName;

    private AddressDTO address;

    private String telephone;

    public LibraryMemberDTO(String memberId, String firstName, String lastName, AddressDTO address, String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMemberId() {
        return memberId;
    }
}
