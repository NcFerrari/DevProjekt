package lp.be.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextEnumTest {

    @Test
    void getText() {
        assertEquals("HH", TextEnum.HOUR_FORMAT.getText());
    }
}