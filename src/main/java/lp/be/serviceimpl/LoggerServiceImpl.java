package lp.be.serviceimpl;

import lp.be.enums.TextEnum;
import lp.be.service.LoggerService;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerServiceImpl implements LoggerService {

    private static LoggerService loggerService;
    private static Logger log;

    public static <T> LoggerService getInstance(Class<T> classLogging) {
        return getInstance(classLogging, false);
    }

    public static <T> LoggerService getInstance(Class<T> classLogging, boolean saveToFile) {
        if (loggerService == null) {
            loggerService = new LoggerServiceImpl();
        }

        if (saveToFile) {
            saveToFile();
        }

        log = Logger.getLogger(classLogging);
        return loggerService;
    }

    private static void saveToFile() {
        String dailyFolder = LocalDate.now().format(DateTimeFormatter.ofPattern(TextEnum.DATE_FORMAT.getText()));
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern(TextEnum.HOUR_FORMAT.getText()));
        DailyRollingFileAppender daily = null;
        try {
            daily = new DailyRollingFileAppender(
                    new PatternLayout(TextEnum.LOG_PATTERN.getText()),
                    String.format(TextEnum.LOG_FILE_FORMAT.getText(), dailyFolder, time),
                    TextEnum.DATE_FORMAT.getText());
        } catch (IOException e) {
            log.trace(e.getMessage());
        }
        Logger.getRootLogger().addAppender(daily);
    }

    private LoggerServiceImpl() {
    }

    @Override
    public Logger getLog() {
        return log;
    }
}
