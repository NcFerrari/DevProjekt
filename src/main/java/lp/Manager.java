package lp;

import lp.be.Data;
import lp.be.dto.Person;
import lp.be.service.LoggerService;
import lp.be.serviceimpl.LoggerServiceImpl;
import lp.fe.swing.ApiOption;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Main class for all application. This is center point where all blocks can share data and call another services.
 */
public class Manager {

    private static Manager manager;
    private final Data data;
    private static final LoggerService logService = LoggerServiceImpl.getInstance(Manager.class);
    private static final Logger log = logService.getLog();

    /**
     * Singleton method
     *
     * @return one instance
     */
    public static Manager getInstance() {
        if (manager == null) {
            manager = new Manager();
        }
        return manager;
    }

    /**
     * Prepare empty "database" object (messenger object)
     */
    private Manager() {
        data = new Data();
    }

    /**
     * v1.0.0 start swing application only
     * v1.0.1 start FX application (with interface to choose swing or FX)
     * v1.0.2 implemented maven, logger, and unit tests
     *
     * @param args no arguments needed
     */
    public static void main(String[] args) {
        log.info("application started");
        ApiOption.showDialog();
    }

    /**
     * Create method from CRUD
     *
     * @param key       Combination first name and surname
     * @param firstName Name of person
     * @param surname   Second name of person
     * @param address   Persons simply address
     * @param phone     Person phone
     * @return result if person is saved:
     * <ul>
     *     <li> 0 = OK</li>
     *     <li>-1 = user already exists in DB</li>
     *     <li>-2 = name or surname is empty</li>
     * </ul>
     */
    public int saveNewPerson(String key, String firstName, String surname, String address, String phone) {
        if (firstName.isBlank() || surname.isBlank()) {
            return -2;
        } else if (data.getPersonDatabase().putIfAbsent(key, new Person(firstName, surname, address, phone)) != null) {
            return -1;
        }
        return 0;
    }

    /**
     * Update method from CRUD
     *
     * @param key     Combination first name and surname
     * @param address Persons simply address
     * @param phone   Person phone
     * @return result if person is saved:
     * <ul>
     *     <li> 0 = OK</li>
     *     <li>-1 = user not exists in database</li>
     * </ul>
     */
    public int editPerson(String key, String address, String phone) {
        if (!data.getPersonDatabase().containsKey(key)) {
            return -1;
        }
        Person person = data.getPersonDatabase().get(key);
        person.setAddress(address);
        person.setPhoneNumber(phone);
        return 0;
    }

    /**
     * Database represents as java class (messenger class). It returned empty or actual collection.
     *
     * @return collection (empty or filled). Collection cannot be null.
     */
    public Collection<Person> getData() {
        if (data.getPersonDatabase().isEmpty()) {
            return new ArrayList<>();
        }
        return data.getPersonDatabase().values();
    }

    /**
     * Removes all data from database.
     */
    public void deleteData() {
        data.getPersonDatabase().clear();
    }
}
