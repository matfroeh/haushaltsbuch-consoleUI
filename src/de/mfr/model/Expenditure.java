package de.mfr.model;

import java.time.LocalDate;

/**
 * Klasse zur Erzeugung von Ausgabenposten (expenditures)
 */

public class Expenditure {

    //region Konstanten
    private static final double DEFAULT_DOUBLE_VALUE = 0;
    private static final String DEFAULT_STRING_VALUE = "none";
    private static final LocalDate DEFAULT_DATE_VALUE = LocalDate.now();
    public static final String CSV_SEPARATOR = ";";
    public static final int INDEX_ATTRIBUTE_DATE = 0;
    public static final int INDEX_ATTRIBUTE_AMOUNT = 1;
    public static final int INDEX_ATTRIBUTE_CATEGORY = 2;
    public static final int INDEX_ATTRIBUTE_DESCRIPTION = 3;
    //endregion

    //region Attribute
    private double amount;
    private LocalDate date;
    private String category;
    private String description;
    //endregion

    //region Konstruktoren
    public Expenditure() {
        amount = DEFAULT_DOUBLE_VALUE;
        date = DEFAULT_DATE_VALUE;
        category = DEFAULT_STRING_VALUE;
        description = DEFAULT_STRING_VALUE;
    }

    public Expenditure(double amount, LocalDate date, String category, String description) {
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.description = description;
    }

    public Expenditure(String csvLine) {
        setAttributesFromCsvLine(csvLine);
    }
    //endregion

    //region Methoden
    public String getAttributesAsCsvLine() {
        return date + CSV_SEPARATOR + amount + CSV_SEPARATOR + category
                + CSV_SEPARATOR + description + "\n";
    }

    public void setAttributesFromCsvLine(String csvLine) {
        String[] attributes = csvLine.split(CSV_SEPARATOR);

        date = LocalDate.parse(attributes[INDEX_ATTRIBUTE_DATE]);
        amount = Double.parseDouble(attributes[INDEX_ATTRIBUTE_AMOUNT]);
        category = attributes[INDEX_ATTRIBUTE_CATEGORY];
        description = attributes[INDEX_ATTRIBUTE_DESCRIPTION];
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Expenditure{" +
                "amount=" + amount +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
//endregion


}
