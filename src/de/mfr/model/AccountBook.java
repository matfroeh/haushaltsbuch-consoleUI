package de.mfr.model;

/**
 *
 *
 *
 *
 *
 *
 *
 *
 * DAS IST LEDIGLICH EINE TEST-KLASSE DIE NICHT VERWENDET WIRD
 *
 *
 *
 *
 *
 *
 *
 */

import java.util.ArrayList;

public class AccountBook {

    //region Konstanten
    //endregion

    //region Attribute
    private ArrayList<Expenditure> expensesList;
    private ArrayList<Double> revenues;
    //endregion

    //region Konstruktoren
    public AccountBook(ArrayList<Expenditure> expensesList, ArrayList<Double> revenues) {
        this.expensesList = expensesList;
        this.revenues = revenues;
    }
    //endregion

    //region Methoden

    public ArrayList<Expenditure> getExpensesList() {
        return expensesList;
    }

    public void setExpensesList(ArrayList<Expenditure> expensesList) {
        this.expensesList = expensesList;
    }

    public ArrayList<Double> getRevenues() {
        return revenues;
    }

    public void setRevenues(ArrayList<Double> revenues) {
        this.revenues = revenues;
    }

    @Override
    public String toString() {
        return "AccountBook{" +
                "expensesList=" + expensesList +
                ", revenues=" + revenues +
                '}';
    }
//endregion
}
