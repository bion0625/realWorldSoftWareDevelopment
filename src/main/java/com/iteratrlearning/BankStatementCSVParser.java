package com.iteratrlearning;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {
    public static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final Integer EXPECTED_ATTRIBUTES_LENGTH = 3;

    @Override
    public BankTransaction parseFrom(final String line) {
        final String[] columns = line.split(",");

        if (columns.length < EXPECTED_ATTRIBUTES_LENGTH) {
            throw new CSVSyntaxException();
        }

        BankStatementValidator validator = new BankStatementValidator(columns[0], columns[1], columns[2]);
        Notification validate = validator.validate();
        if (validate.hasErrors()) {
            System.out.println(validate.errorMessage());
            //todo
        }

        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];

        return new BankTransaction(date, amount, description);
    }

    @Override
    public List<BankTransaction> parseLinesFrom(final List<String> lines) {
        final List<BankTransaction> bankTransactions = new ArrayList<>();
        for (final String line : lines) {
            bankTransactions.add(parseFrom(line));
        }
        return bankTransactions;
    }
}
