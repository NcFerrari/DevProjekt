package lp.be;

import lombok.Getter;
import lp.be.dto.Person;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class Data {

    private final Map<String, Person> personDatabase = new LinkedHashMap<>();
}
