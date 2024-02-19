package lp.fe;

public enum Lang {

    SWING_API("Swing GUI", "Swing GUI"),
    FX_API("FX GUI", "FX GUI"),
    FIRST_NAME("Jméno", "First name"),
    SURNAME("Příjmení", "Surname"),
    ADDRESS("Adresa", "Address"),
    PHONE("Telefon", "Phone"),
    SAVE("Uložit", "Save"),
    EDIT("Upravit", "Edit"),
    LOAD("Načíst", "Load"),
    CLEAR_LOG("Vyčistit log", "Clear"),
    PERSON_NOT_EXISTS("Takový uživatel neexistuje", "This user doesn't exists"),
    PERSON_ALREADY_EXISTS("Takový uživatel už existuje", "User already exists"),
    EMPTY_INPUTS("Musíte zadat Jméno a příjmení", "First name and surname are required"),
    EMPTY("", ""),
    NEW_LINE("\n", "\n"),
    PERSON_FORMAT("%s, %s, %s, %s", "%s, %s, %s, %s");

    private final String czeText;
    private final String engText;

    Lang(String text, String engText) {
        this.czeText = text;
        this.engText = engText;
    }

    public String getCurrentText() {
        return czeText;
    }

    public String getText() {
        return getCurrentText();
    }
}
