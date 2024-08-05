package de.mfr.model;

import java.util.Arrays;

/**
 * Klasse zur Erzeugung einer Einkommensliste. Jeder Eintrag im double Array enthält das Gesamteinkommen für den
 * jeweiligen Jahresmonat.
 */

public class Income {
    public static final double DEFAULT_INITIAL_VALUE = 0.00;
    public static final String TEMPLATE_PRINT_INCOME_FOR_EACH_MONTH = "Monat %d:\t\t%.2f\n";
    //region Konstanten


    //endregion

    //region Attribute
    double[] income = new double[12];
    //endregion

    //region Konstruktoren

    /**
     * Erzeugung des Arrays mit Initialwerten = 0.00 (€)
     */
    public Income() {
        for (double value : income) {
            value = DEFAULT_INITIAL_VALUE;
        }
    }

    /**
     * Erzeugung des Objekts mit einem bereits vorhandenen Einkommens-Array
     *
     * @param income : double[] : Einkommensliste
     */
    public Income(double[] income) {
        for (int i = 0; i < this.income.length; i++) {
            this.income[0] = income[0];
        }
    }
    //endregion

    //region Methoden
    public void printIncomeOfEachMonth() {
        for (int i = 0; i < this.income.length; i++) {
            System.out.printf(TEMPLATE_PRINT_INCOME_FOR_EACH_MONTH, i + 1, this.income[i]);
        }
    }

    public void setIncomeOfMonth(double income, int month) {
        this.income[month - 1] = income;
    }

    public int getSizeOfIncomeArray() {
        return this.income.length;
    }


    public double getIncomeOfMonth(int month) {
        return income[month - 1];
    }

    public double[] getIncome() {
        return income;
    }

    public void setIncome(double[] income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "Income{" +
                "income=" + Arrays.toString(income) +
                '}';
    }
//endregion
}
