package lp.be;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataTest {

    private Data data;

    @BeforeEach
    void setUp() {
        data = new Data();
    }

    @Test
    void temporaryDatabase() {
        assertNotNull(data.getPersonDatabase());
        assertTrue(data.getPersonDatabase().isEmpty());
    }
}