package lp.be.serviceimpl;

import lp.be.service.LoggerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LoggerServiceImplTest {

    @Test
    void getInstance() {
        LoggerService loggerService = LoggerServiceImpl.getInstance(LoggerServiceImplTest.class);
        assertNotNull(loggerService);
    }

    @Test
    void getLog() {
        LoggerService loggerService = LoggerServiceImpl.getInstance(LoggerServiceImplTest.class);
        assertNotNull(loggerService.getLog());
    }
}