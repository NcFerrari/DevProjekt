package lp;

import generator.Human;
import generator.PhoneNumber;
import generator.utils.HumanAtr;
import lp.fe.enums.Lang;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    private final Manager manager = Manager.getInstance();
    private String key;
    private String name;
    private String surname;
    private String address;
    private String phone;

    @BeforeEach
    void setUp() {
        Object[] human = Human.generate(HumanAtr.NAME, HumanAtr.SURNAME, HumanAtr.STREET, HumanAtr.HOUSE_NUMBER);
        name = (String) human[0];
        surname = (String) human[1];
        key = name + Lang.SPACE.getText() + surname;
        address = human[2] + Lang.SPACE.getText() + human[3];
        phone = PhoneNumber.generateStandardPhoneNumber();
        manager.saveNewPerson(key, name, surname, address, phone);
    }

    @AfterEach
    void tearDown() {
        manager.getData().clear();
    }

    @Test
    void saveNewPerson() {
        manager.getData().clear();
        assertEquals(0, manager.getData().size());
        assertEquals(0, manager.saveNewPerson(key, name, surname, address, phone));
        assertEquals(-1, manager.saveNewPerson(key, name, surname, address, phone));
        assertEquals(-2, manager.saveNewPerson(key, Lang.EMPTY.getText(), Lang.EMPTY.getText(), address, phone));
    }

    @Test
    void editPerson() {
        final Object[] human = Human.generate(HumanAtr.STREET, HumanAtr.HOUSE_NUMBER);
        String newAddress = human[0] + Lang.SPACE.getText() + human[1];
        String newPhone = PhoneNumber.generateStandardPhoneNumber();
        manager.getData().forEach(person -> {
            assertEquals(name, person.getFirstName());
            assertEquals(surname, person.getSurName());
            assertEquals(address, person.getAddress());
            assertEquals(phone, person.getPhoneNumber());
        });
        manager.editPerson(key, newAddress, newPhone);
        manager.getData().forEach(person -> {
            assertEquals(name, person.getFirstName());
            assertEquals(surname, person.getSurName());
            assertEquals(newAddress, person.getAddress());
            assertEquals(newPhone, person.getPhoneNumber());
        });
        assertEquals(-1, manager.editPerson(Lang.EMPTY.getText(), newAddress, newPhone));
    }

    @Test
    void getData() {
        assertFalse(manager.getData().isEmpty());
    }

    @Test
    void deleteData() {
        manager.deleteData();
        assertTrue(manager.getData().isEmpty());
    }
}