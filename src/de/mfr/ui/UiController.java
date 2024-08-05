package de.mfr.ui;

import de.mfr.logic.FileHandler;
import de.mfr.model.Expenditure;
import de.mfr.model.Income;
import de.mfr.settings.AppCommands;
import de.mfr.settings.AppTexts;
import de.rhistel.logic.ConsoleReader;
import static java.lang.String.valueOf;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

/**
 * Steuerung des Programms. Eine grundlegende Intention der hierin beschriebenen
 * Steuerung ist es, dass stets das an dem Tag aktuelle Datum beim Start des
 * Programms verwendet wird, d.h. neue Ausgabeneinträge werden standardmäßig als
 * tagesaktuell angesehen. Der Nutzer hat dennoch beim Hinzufügen eines Eintrags
 * die Option, den Monatstag zu ändern. Weiterhin werden durch diese
 * Steuerungslogik alle Ausgaben separat für jeden Monat eines Jahres
 * gespeichert. D.h. insbesondere, dass bei Beginn des nächsten Monats eine neue
 * csv-Datei angelegt wird, ohne dass der Nutzer manuell das Datum wechseln
 * muss. Die Steuerung ermöglicht auch das manuelle Laden von csv-Dateien mit
 * Daten aus (vorangegangen) Monaten, um Einträge bspw. rückdatiert
 * hinzuzufügen/bearbeiten/löschen. Dies erfolgt automatisiert über die Methode
 * changeMonth(), d.h. die Steuerung selbst wählt den richtigen Dateinamen für
 * den ausgewählten Monat des aktuellen Jahres und lädt die Ausgabenliste.
 *
 * Möglicher auftretender unerwünschter Programmablauf: Programm läuft WÄHREND
 * des Monatswechsels von einem auf den nächsten. Dadurch würde die Liste des
 * alten Monats beim Speichern (im neuen Monat) übernommen für den neuen Monat.
 * Analog beim Jahreswechsel bzgl. der Einkommensliste.
 */
public class UiController {

    //region Konstanten
    //public static final double DEFAULT_MONTHLY_INCOME = 2000.22;
    public static final String PATH_NAME_RESOURCES = "resources/";
    public static final String FILE_EXTENSION_CSV = ".csv";
    public static final Locale GERMAN = Locale.GERMAN;
    public static final String PREFIX_INCOME_FILE_NAME = "income";
    //endregion

    //region Attribute
    private static LocalDate today = LocalDate.now();
    private static ArrayList<Expenditure> expendituresList = new ArrayList<>();
    //private static double monthlyIncome = DEFAULT_MONTHLY_INCOME;
    private static Income income;
    //endregion

    //region Konstruktoren
    private UiController() {
    }
    //endregion

    //region Methoden
    public static void startUi() {
        printAppName();
        loadData();
        startMainMenu();
    }

    /**
     * Instanziiert den FileHandler und setzt den Dateipfad der Instanz
     * entsprechend des eingestellten statischen Klassendatums (=
     * "monatsnameJahr.csv"). Lädt die Ausgabenliste über den FileHandler in die
     * ArrayList "expendituresList". Lädt das "Income"-Objekt mit der
     * Jahreseinkommensliste für die monatlichen Einkommen aus der
     * entsprechenden Datei.
     */
    private static void loadData() {
        String filePathExpenses = PATH_NAME_RESOURCES + valueOf(today.getMonth()).toLowerCase() + today.getYear()
                + FILE_EXTENSION_CSV;

        String filePathIncome = PATH_NAME_RESOURCES + PREFIX_INCOME_FILE_NAME + today.getYear()
                + FILE_EXTENSION_CSV;

        FileHandler.getInstance().setFilePathExpenses(filePathExpenses);
        FileHandler.getInstance().setFilePathIncome(filePathIncome);

        checkIncomeFileExistenceOrCreateNewFile(filePathIncome);

        expendituresList = FileHandler.getInstance().readExpendituresFromFile();
        income = FileHandler.getInstance().readIncomeFromFile();
    }

    /**
     * Prüfung ob incomeXXXX.csv - File existiert, da Initialwerte (im Gegensatz
     * zur ExpensesList) benötigt werden (siehe printMainMenuHeader()). Falls
     * nicht, wird über die Income - Klasse eine income-Instanz mit 0.00 -
     * Werten für jeden Monat erzeugt und diese anschließend gespeichert. Das
     * heißt konkret: beim Jahreswechsel wird eine neue incomeJahreszahl.csv
     * angelegt.
     *
     * @param filePathIncome : String : Dateipfadname IncomeXXXX
     */
    private static void checkIncomeFileExistenceOrCreateNewFile(String filePathIncome) {
        boolean incomeFileExists = FileHandler.isFileExisting(filePathIncome);
        if (!incomeFileExists) {
            income = new Income();
            FileHandler.getInstance().saveIncomeToFile(income);
        }
    }

    /**
     * Start des Hauptmenüs. Das Hauptmenü wird nach jeder Operation neu über
     * die Konsole ausgegeben.
     */
    private static void startMainMenu() {
        boolean continueApp = true;

        do {
            printMainMenu();
            int userInputMainMenuItem = ConsoleReader.in.readPositivInt();

            switch (userInputMainMenuItem) {
                case AppCommands.COMMAND_ADD_EXPENDITURE ->
                    addExpenditure();

                case AppCommands.COMMAND_LIST_ALL_EXPENDITURES -> {
                    listAllExpenditures();
                    queryShowSortingMethods();
                }
                case AppCommands.COMMAND_SET_INCOME ->
                    setMonthlyIncome();

                // unnötig, da Balance nun immer im Header des Menüs aktuell berechnet und angezeigt wird
                case AppCommands.COMMAND_CALCULATE_BALANCE ->
                    printBalance();

                case AppCommands.COMMAND_SHOW_EDIT_MENU -> {
                    printEditMenu();
                    selectEditMethod();
                }
                case AppCommands.COMMAND_CHANGE_MONTH -> {
                    changeMonth();
                    queryChangeDayOfMonth();
                }
                case AppCommands.COMMAND_EXIT_MAIN_MENU -> {
                    continueApp = false;
                    System.out.println(AppTexts.TXT_CLOSING_APP);
                }
                default ->
                    System.out.println(AppTexts.TXT_INVALID_INPUT);
            }

        } while (continueApp);
    }

    /**
     * Gibt die Hauptmenüpunkte als einzeilige Kurzversion aus (nicht verwendet
     * aktuell)
     */
    private static void printShortVersionOfMainMenu() {
        System.out.printf(AppTexts.TXT_MAIN_MENU_ITEMS_SHORT_VERSION, AppCommands.COMMAND_ADD_EXPENDITURE,
                AppCommands.COMMAND_LIST_ALL_EXPENDITURES, AppCommands.COMMAND_CALCULATE_BALANCE,
                AppCommands.COMMAND_SHOW_EDIT_MENU, AppCommands.COMMAND_SET_INCOME, AppCommands.COMMAND_CHANGE_MONTH,
                AppCommands.COMMAND_EXIT);
    }

    /**
     * Fügt einen neuen Eintrag in die Ausgabenliste ein. Das Einfügen beginnt
     * mit Eingabe des Betrags. Zur schnelleren Eingabe von tagesaktuellen
     * Ausgaben, wird standardmäßig beim Erfassen des Betrages das Datum des
     * heutigen Tages verwendet, sofern dieser nicht geändert wurde.
     */
    private static void addExpenditure() {
        Expenditure currentExpenditure = new Expenditure();

        setAmountForDateTodayOrSelectDifferentDay(currentExpenditure);
        setCategory(currentExpenditure);
        setDescription(currentExpenditure);

        expendituresList.add(currentExpenditure);

        FileHandler.getInstance().saveExpendituresToFile(expendituresList);
    }

    /**
     * Eingabe des Betrags des neuen Ausgabeneintrags mit Datum des statischen
     * Klassendatums "today". Durch den Befehl "0", kann der Monatstag FÜR
     * DIESEN EINTRAG geändert werden. Das ist bewusst so gemacht worden, damit
     * die Eintragung schnell für den heutigen Tag erfolgen kann, ohne komplett
     * darauf zu verzichten, es ändern zu können.
     *
     * @param currentExpenditure : Expenditure: Ausgabeninstanz
     */
    private static void setAmountForDateTodayOrSelectDifferentDay(Expenditure currentExpenditure) {
        System.out.printf(AppTexts.TXT_ENTER_AMOUNT_OR_PRESS_ZERO_FOR_CHANGING_DATE,
                today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));

        double userInput = ConsoleReader.in.readPositivDouble();

        if (userInput == AppCommands.COMMAND_CHANGE_DAY) {
            setAmountDifferentDay(currentExpenditure);
        } else {
            currentExpenditure.setDate(today);
            setAmount(currentExpenditure, userInput);
        }
    }

    private static void setDescription(Expenditure currentExpenditure) {
        System.out.println(AppTexts.TXT_ADD_DESCRIPTION_TO_EXPENDITURE);
        currentExpenditure.setDescription(ConsoleReader.in.readMandatoryString());
    }

    private static void setCategory(Expenditure currentExpenditure) {
        System.out.println(AppTexts.TXT_ENTER_CATEGORY_OF_EXPENDITURE);
        currentExpenditure.setCategory(ConsoleReader.in.readMandatoryString());
    }

    /**
     * Setzt den Betrag des neuen Ausgabeneintrags mit Änderung des Datums der
     * übergebenen Ausgabeninstanz auf den zuvor validierten Tag.
     *
     * @param currentExpenditure: Expenditure: Ausgabeninstanz
     */
    private static void setAmountDifferentDay(Expenditure currentExpenditure) {
        currentExpenditure.setDate(getValidatedDayFromUser());

        System.out.printf(AppTexts.TXT_DATE_CHANGED_TO_INPUT_VALUE_ENTER_AMOUNT_NEXT,
                currentExpenditure.getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));

        setAmount(currentExpenditure, ConsoleReader.in.readPositivDouble());
    }

    /**
     * Eingabe eines Monatstages über die Konsole, prüft, ob es ein gültiges
     * Datum bei gleichbleibendem Monat und Jahr ist und gibt das Datum als
     * Rückgabewert zurück, sobald ein gültiger Monatstag eingegeben wurde.
     */
    private static LocalDate getValidatedDayFromUser() {
        LocalDate date = null;
        boolean validDay = false;

        do {
            System.out.printf(AppTexts.TXT_QUERY_DATE_OF_EXPENDITURE,
                    today.getMonth().getDisplayName(TextStyle.FULL, GERMAN));

            int userInputDate = ConsoleReader.in.readPositivInt();
            try {
                date = LocalDate.of(today.getYear(), today.getMonth(), userInputDate);
                validDay = true;
            } catch (DateTimeException e) {
                System.out.println(AppTexts.TXT_INVALID_DATE_INPUT_TRY_AGAIN);
            }
        } while (!validDay);
        return date;
    }

    /**
     * Setzt die Größe der Ausgabe für das jeweilige Ausgabenobjekt
     *
     * @param expenditure : Expenditure
     * @param amount : double
     */
    private static void setAmount(Expenditure expenditure, double amount) {
        expenditure.setAmount(amount);
    }

    /**
     * Fragt, ob das Sortiermenü angezeigt werden soll
     */
    private static void queryShowSortingMethods() {
        System.out.println(AppTexts.TXT_QUERY_SHOW_SORT_METHODS);

        if (ConsoleReader.in.readMandatoryAnswerToBinaryQuestion()) {
            chooseSortingMethod();
        }
    }

    /**
     * Ruft zur Wahl einer Sortiermethode auf, sortiert die Ausgabenliste
     * entsprechend der Auswahl und gibt diese anschließend aus. Permanente
     * Speicherung der angezeigten Sortierung erfolgt ERST durch Wahl des
     * Menüpunktes "Speichern".
     */
    private static void chooseSortingMethod() {
        boolean continueSorting = true;

        do {
            printSortingMenu();

            int choice = ConsoleReader.in.readPositivInt();

            switch (choice) {

                case AppCommands.COMMAND_SORT_DATE -> {
                    expendituresList.sort(Comparator.comparing(Expenditure::getDate));
                    listAllExpenditures();
                }
                case AppCommands.COMMAND_SORT_CATEGORY -> {
                    expendituresList.sort(Comparator.comparing(Expenditure::getCategory));
                    listAllExpenditures();
                }
                case AppCommands.COMMANDS_SORT_AMOUNT -> {
                    expendituresList.sort(Comparator.comparing(Expenditure::getAmount));
                    listAllExpenditures();
                }
                case AppCommands.COMMAND_SAVE_SORTED_LIST_TO_FILE ->
                    FileHandler.getInstance().saveExpendituresToFile(expendituresList);

                case AppCommands.COMMAND_EXIT_SORTING ->
                    continueSorting = false;

                default ->
                    System.out.println(AppTexts.TXT_INVALID_INPUT + AppTexts.TXT_PRESS_KEY_TO_STOP_SORTING);
            }

        } while (continueSorting);
    }

    /**
     * Zeigt die verfügbaren Sortiermethoden in einem Menu an.
     */
    private static void printSortingMenu() {
        System.out.println(AppTexts.TXT_QUERY_CHOOSE_SORTING_METHOD);
        for (int i = 0; i < AppTexts.TXT_SORTING_MENU_ITEMS.length; i++) {
            System.out.printf(AppTexts.TEMPLATE_MENU_POINTS, i + 1, AppTexts.TXT_SORTING_MENU_ITEMS[i]);
        }
    }

    /**
     * Ändert den Monat von "today" auf den vom Nutzer gewälten Wert, falls
     * dieser gültig ist. Setzt den Monatstag als Standard auf den Ersten des
     * Monats. Lädt anschließend die neue Ausgabenliste für den jeweiligen Monat
     * und das Income-File für das jeweilige Jahr.
     */
    private static void changeMonth() {
        System.out.printf(AppTexts.TXT_CHOOSE_MONTH_TO_CHANGE, today.getYear());

        boolean validMonth = false;

        do {
            int userInput = ConsoleReader.in.readPositivInt();
            try {
                today = LocalDate.of(today.getYear(), userInput, 1);

                validMonth = true;

            } catch (Exception e) {
                System.out.println(AppTexts.TXT_INVALID_DATE_INPUT_TRY_AGAIN);
            }

        } while (!validMonth);

        loadData();
    }

    /**
     * Fragt, ob nach Aufruf von changeMonth() der Standardmonatstag beibehalten
     * werden soll oder ein vom Nutzer gewähltes Datum eingestellt werden soll,
     * falls die Frage beneint wird.
     */
    private static void queryChangeDayOfMonth() {
        System.out.printf(AppTexts.TXT_QUERY_KEEP_STANDARD_DAY_OF_MONTH,
                today.getDayOfMonth(), today.getMonth().getDisplayName(TextStyle.FULL, GERMAN));

        boolean keepDate = ConsoleReader.in.readMandatoryAnswerToBinaryQuestion();

        if (!keepDate) {
            today = getValidatedDayFromUser();
        }
    }

    /**
     * Ruft zur Auswahl einer Editiermethode auf
     */
    private static void selectEditMethod() {
        int userInput = ConsoleReader.in.readPositivInt();

        switch (userInput) {
            case AppCommands.COMMAND_EDIT_EXPENDITURE ->
                editExpenditure();
            case AppCommands.COMMAND_DELETE_EXPENDITURE ->
                queryIndexForDeletion();
            case AppCommands.COMMAND_BACK_TO_MAIN_MENU ->
                printMainMenu();
            default ->
                System.out.println(AppTexts.TXT_INVALID_INPUT);
        }
    }

    /**
     * Zeigt eine indizierte Ausgabenliste an und gibt die Frage nach dem Index
     * des zu löschenden Eintrags aus. Ruft schließlich die Methode zur Eingabe
     * des Index und zum Löschen des Eintrags auf.
     */
    private static void queryIndexForDeletion() {
        listAllExpendituresIndexed();

        System.out.println(AppTexts.TXT_QUERY_ENTER_INDEX_FOR_DELETION);

        deleteExpenditureAtIndex();
    }

    /**
     * Nutzereingabe des Index des zu löschenden Eintrags. Ungültige Indizes
     * werden abgefangen. Löschung des Eintrags aus der Liste.
     */
    private static void deleteExpenditureAtIndex() {
        boolean validIndex = false;
        do {
            int userInput = ConsoleReader.in.readPositivInt();
            try {
                expendituresList.remove(userInput - 1);

                validIndex = true;

                FileHandler.getInstance().saveExpendituresToFile(expendituresList);

            } catch (Exception e) {
                System.out.println(AppTexts.TXT_INVALID_INDEX_RETRY);
            }
        } while (!validIndex);
    }

    /**
     * Zeigt eine indizierte Ausgabenliste an und gibt die Frage nach dem Index
     * des zu bearbeitenden Eintrags aus. Ruft schließlich die Methode zur
     * Eingabe des Index und zum Bearbeiten des Eintrags auf.
     */
    private static void editExpenditure() {
        listAllExpendituresIndexed();

        System.out.println(AppTexts.TXT_QUERY_ENTER_INDEX);

        editExpenditureAtIndex();
    }

    /**
     * Ändert die Werte eines Ausgabeneintrags. Fängt ungültige Indizes ab und
     * ruft hintereinander die Methoden zum Bearbeiten auf. Speichert Liste
     * permanent nach erfolgreicher Bearbeitung.
     */
    private static void editExpenditureAtIndex() {
        boolean validIndex = false;
        do {
            int userInput = ConsoleReader.in.readPositivInt();
            try {
                Expenditure currentExpenditure = expendituresList.get(userInput - 1);

                currentExpenditure.setDate(getValidatedDayFromUser());

                System.out.println(AppTexts.TXT_ENTER_AMOUNT);
                setAmount(currentExpenditure, ConsoleReader.in.readPositivDouble()); //etwas holprig

                setCategory(currentExpenditure);
                setDescription(currentExpenditure);

                FileHandler.getInstance().saveExpendituresToFile(expendituresList);

                validIndex = true;

            } catch (Exception e) {
                System.out.println(AppTexts.TXT_INVALID_INDEX_RETRY);
            }
        } while (!validIndex);
    }

    /**
     * Listet die verfügbaren Editiermethoden auf.
     */
    private static void printEditMenu() {
        System.out.println(AppTexts.TXT_QUERY_CHOOSE_OPTION);

        for (int i = 0; i < AppTexts.TXT_EDITING_MENU_ITEMS.length; i++) {
            System.out.printf(AppTexts.TEMPLATE_MENU_POINTS, i + 1, AppTexts.TXT_EDITING_MENU_ITEMS[i]);
        }
    }

    /**
     * Gibt die Bilanz als Konsolenzeile aus.
     */
    private static void printBalance() {
        double balance = getBalance();
        System.out.printf(AppTexts.TXT_OUTPUT_BALANCE, balance);
    }

    /**
     * @return balance: double: Differenz aus Einkommen und Ausgabensumme
     */
    private static double getBalance() {
        double sumOfExpenditures = getSumOfExpenditures();
        return income.getIncomeOfMonth(today.getMonthValue()) - sumOfExpenditures;
    }

    /**
     * @return sumOfExpenditures: double: Summe der Ausgaben
     */
    private static double getSumOfExpenditures() {
        double sumOfExpenditures = 0;
        for (Expenditure expenditure : expendituresList) {
            sumOfExpenditures += expenditure.getAmount();
        }
        return sumOfExpenditures;
    }

    /**
     * Setzt das monatliche Gesamteinkommen und speichert die Werte des
     * veränderten Income-Objekts über den FileHandler.
     */
    private static void setMonthlyIncome() {
        System.out.println(AppTexts.TXT_QUERY_ENTER_NEW_INCOME);

        income.setIncomeOfMonth(ConsoleReader.in.readPositivDouble(), today.getMonthValue());

        FileHandler.getInstance().saveIncomeToFile(income);
    }

    /**
     * Gibt eine Liste aller Ausgaben aus. Hängt die Summe aller Ausgaben an das
     * Tabellenende an.
     */
    private static void listAllExpenditures() {
        System.out.printf(AppTexts.TEMPLATE_TABLE_EXPENDITURES_HEADER, AppTexts.TXT_DATUM, AppTexts.TXT_AMOUNT,
                AppTexts.TXT_CATEGORY, AppTexts.TXT_DESCRIPTION);

        for (Expenditure currentExpenditure : expendituresList) {
            System.out.printf(AppTexts.TEMPLATE_TABLE_EXPENDITURES,
                    currentExpenditure.getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)),
                    currentExpenditure.getAmount(), currentExpenditure.getCategory(),
                    currentExpenditure.getDescription());
        }

        printSumOfExpenditures();
    }

    /**
     * Gibt über die Konsole einen formatierten Text mit der Summe der Ausgaben
     * aus, zur Verwendung als Abschlusszeile der Ausgabentabelle.
     */
    private static void printSumOfExpenditures() {
        System.out.println(AppTexts.TXT_DASHED_LINE_SEPARATOR);
        System.out.printf(AppTexts.TXT_OUTPUT_SUM_OF_EXPENDITURES, getSumOfExpenditures());
        System.out.println(AppTexts.TXT_DASHED_LINE_SEPARATOR);
    }

    /**
     * Gibt die Ausgabentabelle indiziert aus.
     */
    private static void listAllExpendituresIndexed() {
        System.out.printf(AppTexts.TEMPLATE_INDEXED_TABLE_EXPENDITURES_HEADER, AppTexts.TXT_INDEX, AppTexts.TXT_DATUM,
                AppTexts.TXT_AMOUNT, AppTexts.TXT_CATEGORY, AppTexts.TXT_DESCRIPTION);

        int index = 1;

        for (Expenditure currentExpenditure : expendituresList) {
            System.out.printf(AppTexts.TEMPLATE_INDEXED_TABLE_EXPENDITURES, index,
                    currentExpenditure.getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)),
                    currentExpenditure.getAmount(), currentExpenditure.getCategory(),
                    currentExpenditure.getDescription());
            index++;
        }
    }

    private static void printMainMenu() {
        printMainMenuHeader();
        printMainMenuItems();
        printMainMenuFooter();
    }

    private static void printMainMenuHeader() {
        System.out.printf(AppTexts.TXT_MAIN_MENU_HEADER,
                today.getMonth().getDisplayName(TextStyle.FULL, GERMAN), income.getIncomeOfMonth(today.getMonthValue()),
                getBalance(), getSumOfExpenditures());
    }

    private static void printMainMenuItems() {
        for (int i = 0; i < AppTexts.TXT_MAIN_MENU_ITEMS.length; i++) {
            System.out.printf(AppTexts.TEMPLATE_MENU_POINTS, i + 1, AppTexts.TXT_MAIN_MENU_ITEMS[i]);
        }
    }

    private static void printMainMenuFooter() {
        System.out.printf(AppTexts.TXT_MAIN_MENU_FOOTER, AppCommands.COMMAND_EXIT);
    }

    private static void printAppName() {
        System.out.println(AppTexts.TXT_APP_NAME);
    }
    //endregion
}
