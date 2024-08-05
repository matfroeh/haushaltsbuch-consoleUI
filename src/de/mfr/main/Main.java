package de.mfr.main;

import de.mfr.ui.UiController;

import java.time.LocalDate;
import java.time.format.FormatStyle;
import java.util.Date;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofLocalizedDate;

public class Main {

    public static void main(String[] args) {
        UiController.startUi();
    }
}