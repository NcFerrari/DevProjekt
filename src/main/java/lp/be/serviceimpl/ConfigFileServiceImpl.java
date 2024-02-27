package lp.be.serviceimpl;

import lp.be.enums.TextEnum;
import lp.be.service.ConfigFileService;
import lp.be.service.LoggerService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileServiceImpl implements ConfigFileService {

    private static ConfigFileServiceImpl configFileService;
    private final LoggerService loggerService = LoggerServiceImpl.getInstance(ConfigFileServiceImpl.class);
    private final Logger log = loggerService.getLog();

    public static ConfigFileServiceImpl getInstance() {
        if (configFileService == null) {
            configFileService = new ConfigFileServiceImpl();
        }
        return configFileService;
    }

    private ConfigFileServiceImpl() {
    }

    @Override
    public String getValue(String key) {
        Properties properties = loadFile(getClass().getClassLoader()
                .getResourceAsStream(TextEnum.CONFIG_FILE_NAME.getText()));
        return properties.getProperty(key);
    }

    @Override
    public int getIntValue(String key) {
        return Integer.parseInt(getValue(key));
    }

    private Properties loadFile(InputStream inputStream) {
        Properties properties = null;
        try {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return properties;
    }
}
