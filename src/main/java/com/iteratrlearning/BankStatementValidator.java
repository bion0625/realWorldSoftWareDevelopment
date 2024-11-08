package com.iteratrlearning;

import java.time.DateTimeException;
import java.time.LocalDate;

public class BankStatementValidator {

    private final String description;
    private final String date;
    private final String amount;

    public BankStatementValidator(String description, String date, String amount) {
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
            parsedDate = LocalDate.parse(this.date);
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
