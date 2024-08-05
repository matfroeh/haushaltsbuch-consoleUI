package de.mfr.settings;

public class AppTexts {
    //region Konstanten
    public static final String TEMPLATE_MENU_POINTS = "( %d ) %s\n";
    public static final String[] TXT_MAIN_MENU_ITEMS = new String[] {"Ausgabe eintragen",
            "Alle Ausgaben anzeigen", "Bilanzierung neuberechnen", "Ausgaben bearbeiten/löschen",
            "Monatseinnahmen festlegen", "Monat wechseln"};
    public static final String[] TXT_SORTING_MENU_ITEMS = new String[] {"Datum", "Kategorie", "Betrag",
            "Sortierte Liste speichern", "Sortierung beenden"};
    public static final String[] TXT_EDITING_MENU_ITEMS = new String[] {"Bearbeiten", "Löschen", "Zurück"};
    public static final String TXT_DATE_CHANGED_TO_INPUT_VALUE_ENTER_AMOUNT_NEXT = "Der Tag wurde auf den %s geändert. "
            + "Bitte nun die Größe des Betrags eingeben.\n";
    public static final String TXT_QUERY_DATE_OF_EXPENDITURE = "Bitte den Tag des Monats %s als ganze Zahl eingeben.\n";
    public static final String TXT_INVALID_DATE_INPUT_TRY_AGAIN = "Ungültige Eingabe. Bitte wiederholen und den Tag " +
            "als Ganzzahl eingeben.\n" +
            "--------------------------------------------------------------------------";
    public static final String TXT_ENTER_AMOUNT_OR_PRESS_ZERO_FOR_CHANGING_DATE = "Bitte die Größe des " +
            "Ausgabenbetrags für den gewählten Tag (%s) eingeben. " +
            "['0' eingeben, um den Tag des Monats zu ändern]\n";
    public static final String TXT_INVALID_INPUT = "Ungültige Eingabe. ";
    public static final String TXT_PRESS_KEY_TO_STOP_SORTING = "\"" + AppCommands.COMMAND_EXIT_SORTING + "\""
            + " eingeben um die Sortierung zu beenden.";
    public static final String TXT_DATUM = "Datum";
    public static final String TXT_AMOUNT = "Betrag in €";
    public static final String TXT_CATEGORY = "Kategorie";
    public static final String TXT_DESCRIPTION = "Beschreibung";
    public static final String TXT_CLOSING_APP = "Das Programm wird beendet...";
    public static final String TXT_QUERY_CHOOSE_SORTING_METHOD = "Nach welcher Eigenschaft " +
            "soll die Liste sortiert werden?";
    public static final String TXT_OUTPUT_BALANCE = "Die Bilanz für den Monat beträgt %.2f €.\n";
    public static final String TXT_QUERY_ENTER_NEW_INCOME = "Bitte das neue Gesamteinkommen des Monats eingeben";
    public static final String TEMPLATE_TABLE_EXPENDITURES = "%5s %12.2f € %20s %25s\n";
    public static final String TEMPLATE_TABLE_EXPENDITURES_HEADER = """
            %5s %17s %20s %25s
            ------------------------------------------------------------------------
            """;
    public static final String TEMPLATE_INDEXED_TABLE_EXPENDITURES = "%-8d %5s %12.2f € %20s %25s\n";
    public static final String TEMPLATE_INDEXED_TABLE_EXPENDITURES_HEADER = """
            %-8s %5s %17s %20s %25s
            ------------------------------------------------------------------------
            """;
    public static final String TXT_ADD_DESCRIPTION_TO_EXPENDITURE = "Bitte eine Beschreibung der Ausgabe hinzufügen.";
    public static final String TXT_ENTER_CATEGORY_OF_EXPENDITURE = "Bitte die Kategorie der Ausgabe eingeben.";
    public static final String TXT_QUERY_SHOW_SORT_METHODS = "Sollen die Sortierfunktionen aufgerufen werden?";
    public static final String TXT_MAIN_MENU_HEADER = """
            ===============================================
                            HAUPTMENÜ
            -----------------------------------------------
            Monat: %s           Einnahmen: %.2f €
            Bilanz: %.2f €          Ausgaben: %5.2f €
            -----------------------------------------------
            """;
    public static final String TXT_MAIN_MENU_FOOTER = """
            -----------------------------------------------
            ( %d ) Haushaltsbuch beenden
            ===============================================
                """;
    public static final String TXT_APP_NAME = """
            ===============================================
                          HAUSHALTSBUCH""";
    public static final String TXT_MAIN_MENU_ITEMS_SHORT_VERSION = "%d = EINTRAGEN | %d = ANZEIGEN | " +
            "%d = BILANZ | %d = BEARBEITEN/LÖSCHEN | %d = MONATSEINNAHMEN FESTLEGEN | %d = MONAT WECHSELN | " +
            "%d = BEENDEN\n";
    public static final String TXT_QUERY_CHOOSE_OPTION = "Bitte die gewünschte Option wählen.";
    public static final String TXT_INDEX = "Index";
    public static final String TXT_ENTER_AMOUNT = "Bitten den Betrag eingeben.";
    public static final String TXT_INVALID_INDEX_RETRY = "Ungültiger Index. Bitte wiederholen";
    public static final String TXT_QUERY_ENTER_INDEX = "Bitte den Index des zu bearbeitenden Eintrags auswählen.";
    public static final String TXT_QUERY_ENTER_INDEX_FOR_DELETION = "Bitte den Index des zu löschenden Eintrags auswählen.";
    public static final String TXT_CHOOSE_MONTH_TO_CHANGE = "Auf welchen Monat des Jahres %s soll gewechselt werden?\n" + "Bitte den Monat als ganze Zahl eingeben.\n";
    public static final String TXT_DASHED_LINE_SEPARATOR = "------------------------------------------------------------------------";
    public static final String TXT_OUTPUT_SUM_OF_EXPENDITURES = "Die Gesamtausgaben betragen: %.2f €\n";
    public static final String TXT_ERROR_WRITING_TO_FILE = "Ein Fehler beim Schreibvorgang ist aufgetreten.";
    public static final String TXT_ERROR_CLOSING_FILE = "Ein Fehler beim Schließen der Datei ist aufgetreten";
    public static final String TXT_ERROR_READING_FILE = "Ein Fehler beim Lesen der Datei ist aufgetreten.";
    public static final String TXT_QUERY_KEEP_STANDARD_DAY_OF_MONTH = "Soll die Standardauswahl des Monatstages (%d. %s) beibehalten werden\n";
    //endregion

    //region Attribute
    //endregion

    //region Konstruktoren
    //endregion

    //region Methoden
    //endregion
}
