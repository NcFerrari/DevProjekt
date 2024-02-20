package lp.be.enums;

import lombok.Getter;

@Getter
public enum TextEnum {

    DATE_FORMAT("yyyy-MM-dd"),
    HOUR_FORMAT("HH"),
    LOG_PATTERN("[%d] %-8p (%-25c): %m%n"),
    LOG_FILE_FORMAT("logs/%s/%s.log");

    private final String text;

    TextEnum(String text) {
        this.text = text;
    }
}
