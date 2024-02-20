package lp;

import generator.service.LoggerService;
import lp.be.Data;
import lp.be.dto.Person;
import lp.be.serviceimpl.LoggerServiceImpl;
import lp.fe.swing.ApiOption;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;

public class Manager {

    private static Manager manager;
    private final Data data;
    private static final LoggerService logService = LoggerServiceImpl.getInstance(Manager.class);
    private static final Logger log = logService.getLog();

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
        log.info("application started");
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
