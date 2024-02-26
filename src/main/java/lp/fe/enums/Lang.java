package lp.fe.enums;

public enum Lang {

    APPLICATION_STARTED("Aplikace se spustila", "Application started"),
    SWING_API("Swing GUI", "Swing GUI"),
    FX_API("FX GUI", "FX GUI"),
    FIRST_NAME("Jméno", "First name"),
    SURNAME("Příjmení", "Surname"),
    ADDRESS("Adresa", "Address"),
    PHONE("Telefon", "Phone"),
    SAVE("Uložit", "Save"),
    EDIT("Upravit", "Edit"),
    LOAD("Načíst", "Load"),
    DELETE("Smazat data", "Delete data"),
    CLEAR_LOG("Vyčistit log", "Clear"),
    PERSON_NOT_EXISTS("Takový uživatel neexistuje", "This user doesn't exists"),
    PERSON_ALREADY_EXISTS("Takový uživatel už existuje", "User already exists"),
    EMPTY_INPUTS("Musíte zadat Jméno a příjmení", "First name and surname are required"),
    EMPTY("", ""),
    SPACE(" ", " "),
    NEW_LINE("\n", "\n"),
    PERSON_FORMAT("%s, %s, %s, %s", "%s, %s, %s, %s"),
    SUCCESSFULLY_SAVED("Uživatel úspěšně uložen", "Person successfully saved"),
    SUCCESSFULLY_EDITED("Uživatel úspěšně upraven", "Person successfully edited");

    private final String czeText;
    private final String engText;

    /**
     * Ordinary setting enumeration values
     *
     * @param czeText first text (may it be problem with diacritic)
     * @param engText next text
     */
    Lang(String czeText, String engText) {
        this.czeText = czeText;
        this.engText = engText;
    }

    /**
     * Selected language
     *
     * @return texts in selected language
     */
    public String getText() {
        return czeText;
    }
}
