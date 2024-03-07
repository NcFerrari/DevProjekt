package lp.be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lp.fe.enums.Lang;

/**
 * Standard messenger class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String firstName;
    private String surName;
    private String address;
    private String phoneNumber;

    @Override
    public String toString() {
        return String.format(Lang.PERSON_FORMAT.getText(), firstName, surName, address, phoneNumber);
    }
}
