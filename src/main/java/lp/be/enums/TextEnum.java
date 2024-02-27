package lp.be.enums;

import lombok.Getter;

/**
 * This class represents own convention. All classes don't contain any raw String in code. For this mechanic this
 * enumeration serve. Even for empty String use this enum.
 */
@Getter
public enum TextEnum {

    DATE_FORMAT("yyyy-MM-dd"),
    HOUR_FORMAT("HH"),
    LOG_PATTERN("[%d] %-8p (%-25c): %m%n"),
    LOG_FILE_FORMAT("logs/%s/%s.log"),
    FILE_NOT_FOUND("Cannot find file %s"),
    CONFIG_FILE_NAME("config.properties"),
    DIALOG_WIDTH("dialog.width"),
    DIALOG_HEIGHT("dialog.height"),
    APPLICATION_WIDTH("application.width"),
    APPLICATION_HEIGHT("application.height");

    private final String text;

    /**
     * @param text Return texts instead of raw String in all codes
     */
    TextEnum(String text) {
        this.text = text;
    }
}
