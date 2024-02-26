package lp.fe.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LangTest {

    @Test
    void getText() {
        assertTrue("Application started".equals(Lang.APPLICATION_STARTED.getText()) ||
                "Aplikace se spustila".equals(Lang.APPLICATION_STARTED.getText()));
    }
}