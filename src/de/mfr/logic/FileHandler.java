package de.mfr.logic;

import de.mfr.model.Expenditure;
import de.mfr.model.Income;
import de.mfr.settings.AppTexts;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Speichert die in der ArrayList<Expenditures> vorhanden Elemente in eine csv-Datei bzw. liest diese aus einer
 * csv-Datei und erzeugt eine ArrayList mit diesen gespeicherten Elementen.
 */
public class FileHandler {
    public static final String CSV_NEWLINE = "\n";
    //region Konstanten
    //endregion

    //region Attribute
    private static FileHandler instance;
    private static String filePathExpenses;
    private static String filePathIncome;
    //endregion

    //region Konstruktoren
    private FileHandler(String filePathExpenses, String filePathIncome) {
        FileHandler.filePathExpenses = filePathExpenses;
        FileHandler.filePathIncome = filePathIncome;
    }
    //endregion

    //region Methoden

    /**
     * Methode zum dynamischen Wechsel des Dateipfadnamens. Wird beim Wechsel des Monats vom UI-Controller verwendet.
     * @param filePathExpenses : String : Dateipfad/Dateiname.csv
     */
    public synchronized void setFilePathExpenses(String filePathExpenses) {
        FileHandler.filePathExpenses = filePathExpenses;
    }

    public synchronized void setFilePathIncome(String filePathIncome) {
        FileHandler.filePathIncome = filePathIncome;
    }

    /**
     * Instanziierung als Singleton
     * @return instance : FileHandler : Singleton-Instanz
     */
    public static synchronized FileHandler getInstance() {
        if (instance == null) {
            instance = new FileHandler(filePathExpenses, filePathIncome);
        }
        return instance;
    }

    public static boolean isFileExisting(String filePath) {
        File fileToCheck = new File(filePath);

        return fileToCheck.exists();
    }

    /**
     *  Speichert das Income-Array in eine csv-Datei
     */
    public void saveIncomeToFile(Income income) {
        File csvFile = new File(filePathIncome);

        FileOutputStream fos;
        OutputStreamWriter osw;
        BufferedWriter out = null;

        try {
            fos = new FileOutputStream(csvFile);
            osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            out = new BufferedWriter(osw);

            for (int i = 1; i <= income.getSizeOfIncomeArray(); i++) {
                out.write(income.getIncomeOfMonth(i) + CSV_NEWLINE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(AppTexts.TXT_ERROR_WRITING_TO_FILE);

        } finally {
            try {
                out.close();
            } catch (Exception e) {
                System.out.println(AppTexts.TXT_ERROR_CLOSING_FILE);
            }
        }
    }

    /**
     * Liest aus einer csv-Datei zeilenweise das Einkommen des jeweiligen Monats ein und
     * gibt ein "Income"-Objekt als Rückgabewert aus
     * @return income : Income : monatliches Einkommen
     */
    public Income readIncomeFromFile() {
        Income income = new Income();

        File csvFile = new File(filePathIncome);

        FileInputStream fis;
        InputStreamReader isr;
        BufferedReader in = null;

        try {
            fis = new FileInputStream(csvFile);
            isr = new InputStreamReader(fis);
            in = new BufferedReader(isr);

            boolean endOfFile = false;

            for (int i = 1; i <= income.getSizeOfIncomeArray(); i++) {
                String csvLine = in.readLine();

                if (csvLine == null) endOfFile = true;

                else {
                    income.setIncomeOfMonth(Double.parseDouble(csvLine), i);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(AppTexts.TXT_ERROR_READING_FILE);

        } finally {
            try {
                in.close();
            } catch (Exception e) {
                System.out.println(AppTexts.TXT_ERROR_CLOSING_FILE);
            }
        }
        return income;
    }

    /**
     * Speichert jedes ArrayList-Element zeilenweise
     * @param expendituresList
     */
    public void saveExpendituresToFile(List<Expenditure> expendituresList) {
        File csvFile = new File(filePathExpenses);

        FileOutputStream fos;
        OutputStreamWriter osw;
        BufferedWriter out = null;

        try {
            fos = new FileOutputStream(csvFile);
            osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            out = new BufferedWriter(osw);

            for (Expenditure currentExpenditure : expendituresList) {
                out.write(currentExpenditure.getAttributesAsCsvLine());
            }

        } catch (Exception e) {
            System.out.println(AppTexts.TXT_ERROR_WRITING_TO_FILE);

        } finally {
            try {
                out.close();
            } catch (Exception e) {
                System.out.println(AppTexts.TXT_ERROR_CLOSING_FILE);
            }
        }
    }

    /**
     * Liest aus eine csv-Datei zeilenweise ein und erzeugt daraus die jeweiligen Expenditure-Objekte,
     * die in eine ArrayList eingefügt werden.
     * @return expenditureList : ArrayList<Expenditures> : Ausgabenliste
     */
    public ArrayList<Expenditure> readExpendituresFromFile() {
        ArrayList<Expenditure> expendituresList = new ArrayList<>();

        File csvFile = new File(filePathExpenses);

        FileInputStream fis;
        InputStreamReader isr;
        BufferedReader in = null;

        try {
            fis = new FileInputStream(csvFile);
            isr = new InputStreamReader(fis);
            in = new BufferedReader(isr);

            boolean endOfFile = false;

            while (!endOfFile) {
                String csvLine = in.readLine();

                if (csvLine == null) endOfFile = true;
                else {
                    expendituresList.add(new Expenditure(csvLine));
                }
            }

        } catch (Exception e) {
            System.out.println(AppTexts.TXT_ERROR_READING_FILE);

        } finally {
            try {
                in.close();
            } catch (Exception e) {
                System.out.println(AppTexts.TXT_ERROR_CLOSING_FILE);
            }
        }
        return expendituresList;
    }


//endregion

}

