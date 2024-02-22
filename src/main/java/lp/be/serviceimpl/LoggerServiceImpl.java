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

    /**
     * Singleton basic method (overloaded)
     *
     * @param classLogging Class from which one this method is called. Don't forget .class suffix
     * @return LoggerServiceImpl what is object of this interface
     */
    public static <T> LoggerService getInstance(Class<T> classLogging) {
        return getInstance(classLogging, false);
    }

    /**
     * Singleton basic method
     *
     * @param classLogging Class from which one this method is called. Don't forget .class suffix
     * @param saveToFile   This is switch how to swap between recording all outputs into log file
     * @return LoggerServiceImpl what is object of this interface
     */
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

    /**
     * When you set saveToFile value as true in method {@link #getInstance(Class, boolean)} you use this method.
     * This method generate directories and files for output.Names are generated from date and time in log root
     * directory. Days are directories (names are dates) and logs are
     * in files named by hour.<br>
     * For example:<br><br>
     * logs<br>
     * -2024-01-03<br>
     * --14.log<br>
     * -2024-02-22<br>
     * --09.log<br><br>
     * This means that there are 2 log files. First one is from 3.1.2024 at 2pm. Second one is from 22.2.2024 at 9am.
     */
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

    /**
     * Singleton basic implementation
     */
    private LoggerServiceImpl() {
    }

    /**
     * By log you can use methods like:
     * <ul>
     *     <li>trace()</li>
     *     <li>debug()</li>
     *     <li>info()</li>
     *     <li>warn()</li>
     *     <li>error()</li>
     *     <li>fatal()</li>
     * </ul>
     *
     * @return Logger for print message into output
     */
    @Override
    public Logger getLog() {
        return log;
    }
}
