package lp;

import lp.be.Data;
import lp.be.dto.Person;
import lp.fe.swing.ApiOption;

import java.util.ArrayList;
import java.util.Collection;

public class Manager {

    private static Manager manager;
    private final Data data;

    public static Manager getInstance() {
        if (manager == null) {
            manager = new Manager();
        }
        return manager;
    }

    private Manager() {
        data = new Data();
    }

    public static void main(String[] args) {
        ApiOption.showDialog();
    }

    public int saveNewPerson(String key, String firstName, String surname, String address, String phone) {
        if (firstName.isBlank() || surname.isBlank()) {
            return -2;
        } else if (data.getPersonDatabase().putIfAbsent(key, new Person(firstName, surname, address, phone)) != null) {
            return -1;
        }
        return 0;
    }

    public int editPerson(String key, String address, String phone) {
        if (!data.getPersonDatabase().containsKey(key)) {
            return -1;
        }
        Person person = data.getPersonDatabase().get(key);
        person.setAddress(address);
        person.setPhoneNumber(phone);
        return 0;
    }

    public Collection<Person> getData() {
        if (data.getPersonDatabase().isEmpty()) {
            return new ArrayList<>();
        }
        return data.getPersonDatabase().values();
    }
}
