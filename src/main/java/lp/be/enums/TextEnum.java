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
    LOG_FILE_FORMAT("logs/%s/%s.log");

    private final String text;

    /**
     * @param text Return texts instead of raw String in all codes
     */
    TextEnum(String text) {
        this.text = text;
    }
}
