package de.mfr.test;

import de.mfr.logic.FileHandler;
import de.mfr.model.AccountBook;
import de.mfr.model.Expenditure;
import de.mfr.model.Income;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.*;

import static java.time.format.DateTimeFormatter.ofLocalizedDate;

public class TestMain {
    //region Konstanten
    //endregion

    //region Attribute
    //endregion

    //region Konstruktoren
    //endregion

    //region Methoden
    public static void main(String[] args) {

        //testsWithCombinedList();

        //firstTestsWithLocalDateClass();

        //testingSortingOfExpenditureListBasedOnDate();

        //testAccountBookClass();

        testIncomeClassAndFileHandlerModifications();
    }



    private static void testIncomeClassAndFileHandlerModifications() {
        Income income = new Income();
        income.printIncomeOfEachMonth();
        income.setIncomeOfMonth(3333.33, 12);
        income.setIncomeOfMonth(1111.11, 1);
        income.printIncomeOfEachMonth();
        System.out.println(income);

        // Test FileHandler Income
        //FileHandler.getInstance().setFilePathExpenses();
        FileHandler.getInstance().setFilePathIncome("resources/testIncome.csv");

        FileHandler.getInstance().saveIncomeToFile(income);

        Income incomeFromFile = FileHandler.getInstance().readIncomeFromFile();

        incomeFromFile.printIncomeOfEachMonth();

        //FileHandler fileExists
        System.out.println(FileHandler.getInstance().isFileExisting("resources/income2024.csv"));

        incomeFromFile = FileHandler.getInstance().readIncomeFromFile();

    }

    private static void testAccountBookClass() {
        ArrayList<Expenditure> expensesList = createExpendituresList();
        ArrayList<Double> revenues = new ArrayList<>();
        revenues.add(2000.00);
        revenues.add(22.22);

        AccountBook fullAccount = new AccountBook(expensesList, revenues);
        System.out.println(fullAccount);
        System.out.println(fullAccount.getRevenues());

    }

    private static ArrayList<Expenditure> createExpendituresList() {
        ArrayList<Expenditure> expendituresList = new ArrayList<>();
        expendituresList.add(new Expenditure());
        expendituresList.add(new Expenditure(12.99, LocalDate.now(), "Fleisch", "Huhn"));
        expendituresList.add(new Expenditure(2.99, LocalDate.of(1999, 4, 30), "Haushalt", "Glasreiniger"));
        expendituresList.add(new Expenditure(9.99, LocalDate.of(2011, 1, 31), "Fleisch", "Putenbrust"));
        return expendituresList;
    }

    private static void testsWithCombinedList() {
        Expenditure itemOne = new Expenditure(300, LocalDate.now(), "Lebensmittel", "Brot");
        Expenditure itemTwo = new Expenditure(20, LocalDate.of(1999, 7, 21), "Haushalt", "Waschmittel");

        ArrayList<Expenditure> expensesList = new ArrayList<>();
        expensesList.add(itemOne);
        expensesList.add(itemTwo);
        System.out.println(expensesList);

        double income = 1000.99;

        List<Object> combinedList = new ArrayList<>();
        combinedList.add(expensesList);
        combinedList.add(income);
        System.out.println(combinedList);
        System.out.println(combinedList.get(combinedList.lastIndexOf(income)));




    }

    private static void testingSortingOfExpenditureListBasedOnDate() {
        LocalDate dateOne;
        LocalDate dateTwo = LocalDate.of(2021, 2, 27);

        ArrayList<LocalDate> dateList = new ArrayList<>();
        dateList.add(LocalDate.now());
        dateList.add(dateTwo);
        dateList.add(LocalDate.of(2021, 2, 28));
        dateList.add(LocalDate.of(2021, 3, 26));
        dateList.add(LocalDate.of(2024, 12, 1));

        System.out.println(dateList);

        dateList.sort(Comparator.naturalOrder());

        System.out.println(dateList);

        Expenditure expenditureOne = new Expenditure();
        expenditureOne.setDate(dateTwo);
        Expenditure expenditureTwo = new Expenditure(20, LocalDate.of(1999, 7, 21), "Haus", "nix");

        System.out.println(expenditureOne);
        System.out.println(expenditureTwo);

        ArrayList<Expenditure> ausgabenListe = new ArrayList<>();
        ausgabenListe.add(expenditureOne);
        ausgabenListe.add(expenditureTwo);
        System.out.println(ausgabenListe);

        ausgabenListe.sort(Comparator.comparing(Expenditure::getDate));

        System.out.println(ausgabenListe);

        dateOne = LocalDate.parse("2023-10-23");
        System.out.println(dateOne);

    }


    private static void firstTestsWithLocalDateClass() {
        String datumString = "01.09.2023";

        System.out.println(datumString);

        LocalDate date = LocalDate.now();
        System.out.println(date);

        String dateAsString;
        dateAsString = date.format(ofLocalizedDate(FormatStyle.SHORT));

        System.out.println(dateAsString);

        CharSequence dateAsCharSequence = "21.09.2022";
        date = LocalDate.parse(dateAsCharSequence, ofLocalizedDate(FormatStyle.MEDIUM));

        System.out.println(date);

        date = LocalDate.now();

        System.out.println(date.getDayOfWeek());

        try {
            System.out.println(LocalDate.parse("2007-02-29"));
        } catch (DateTimeException e) {
            System.out.printf("Ungültiger Tag: %s \n", e.getMessage());
        }

        System.out.println(date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.GERMAN));
        System.out.println(date.getMonth().getDisplayName(TextStyle.FULL, Locale.GERMAN));

        LocalDate dateTwo = LocalDate.parse("2023-10-23");
        System.out.println(dateTwo);


    }
    //endregion

}
