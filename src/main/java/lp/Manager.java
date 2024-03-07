package lp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lp.be.Data;
import lp.be.dto.Person;
import lp.be.enums.TextEnum;
import lp.be.service.LoggerService;
import lp.be.serviceimpl.LoggerServiceImpl;
import lp.fe.swing.ApiOption;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Main class for all application. This is center point where all blocks can share data and call another services.
 */
public class Manager {

    private static final LoggerService LOGGER_SERVICE = LoggerServiceImpl.getInstance(Manager.class);
    private static final Logger LOG = LOGGER_SERVICE.getLog();
    private static Manager manager;
    private final Data data;

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
        ApiOption.showDialog();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        try {
            Person person = mapper.readValue(Manager.class.getClassLoader()
                    .getResourceAsStream(TextEnum.CONFIG_YAML_NAME.getText()), Person.class);
            LOG.info(person);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
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
