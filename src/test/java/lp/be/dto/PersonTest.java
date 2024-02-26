package lp.be.dto;

import generator.Human;
import generator.PhoneNumber;
import generator.utils.HumanAtr;
import lp.fe.enums.Lang;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private Person person;
    private String name;
    private String surname;
    private String address;
    private String phone;

    @BeforeEach
    void setUp() {
        Object[] human = Human.generate(HumanAtr.NAME, HumanAtr.SURNAME, HumanAtr.STREET, HumanAtr.HOUSE_NUMBER);
        name = (String) human[0];
        surname = (String) human[1];
        address = human[2] + Lang.SPACE.getText() + human[3];
        phone = PhoneNumber.generateStandardPhoneNumber();
        person = new Person(name, surname, address, phone);
    }

    @Test
    void testToString() {
        final String exceptResult = String.format(Lang.PERSON_FORMAT.getText(), name, surname, address, phone);
        assertEquals(exceptResult, person.toString());
    }
}