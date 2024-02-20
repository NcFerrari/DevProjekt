package lp.be.dto;

import lombok.Setter;
import lp.fe.enums.Lang;

@Setter
public class Person {

    private final String firstName;
    private final String surName;
    private String address;
    private String phoneNumber;

    public Person(String firstName, String surName, String address, String phoneNumber) {
        this.firstName = firstName;
        this.surName = surName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return String.format(Lang.PERSON_FORMAT.getText(), firstName, surName, address, phoneNumber);
    }
}
