package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LibraryMember extends Person implements Serializable {
    private static final long serialVersionUID = -2226197306790714013L;

    private String memberId;

    private List<CheckoutRecord> checkoutRecordList;

    public LibraryMember(String memberId, String firstName, String lastName, Address address, String telephone) {
        super(firstName, lastName, address, telephone);
        this.memberId = memberId;
        this.checkoutRecordList = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public List<CheckoutRecord> getCheckoutRecordList() {
        return checkoutRecordList;
    }

    public void addCheckoutRecord(CheckoutRecord checkoutRecord) {
        this.checkoutRecordList.add(checkoutRecord);
    }
}
