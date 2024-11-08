package com.iteratrlearning;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BankStatementValidator {

    private final String description;
    private final String date;
    private final String amount;

    public BankStatementValidator(String date, String amount, String description) {
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    public Notification validate() {
        final Notification notification = new Notification();
        if (this.description.length() > 100) {
            notification.addError("The description is too long");
        }

        final LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(this.date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            if (parsedDate.isAfter(LocalDate.now())) {
                notification.addError("date cannot be in the future");
            }
        }
        catch (DateTimeException e) {
            notification.addError("Invalid format for date");
        }
        final double amount;
        try {
            amount = Double.parseDouble(this.amount);
        }
        catch (NumberFormatException e) {
            notification.addError("Invalid format for amount");
        }
        return notification;
    }
}
