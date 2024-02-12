package lp.be;

import lp.be.dto.Person;

import java.util.LinkedHashMap;
import java.util.Map;

public class Data {

    private final Map<String, Person> personDatabase = new LinkedHashMap<>();

    public Map<String, Person> getPersonDatabase() {
        return personDatabase;
    }
}
