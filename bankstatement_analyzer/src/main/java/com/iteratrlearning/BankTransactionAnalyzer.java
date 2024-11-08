package com.iteratrlearning;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class BankTransactionAnalyzer {
    private static final String RESOURCES = "src/main/resources";

    public void analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException {
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);

        final BankTransactionProcessor bankTransactionProcessor = new BankTransactionProcessor(bankTransactions);

        collectSummary(bankTransactionProcessor);
    }

    private void collectSummary(final BankTransactionProcessor bankTransactionProcessor) {
        System.out.println("The total for all transactions is " + bankTransactionProcessor.calculateTotalAmount());

        System.out.println("The total for transactions in January " + bankTransactionProcessor.calculateTotalInMonth(Month.JANUARY));

        System.out.println("The total for transactions in february " + bankTransactionProcessor.calculateTotalInMonth(Month.FEBRUARY));

        System.out.println("The total salary received is " + bankTransactionProcessor.calculateTotalForCategory("Salary"));

//        final List<com.iteratrlearning.BankTransaction> transactions = bankTransactionProcessor.findTransactions(new com.iteratrlearning.BankTransactionInFebruaryAndExpensive());
        final List<BankTransaction> transactions = bankTransactionProcessor.findTransactions(
                bankTransaction -> bankTransaction.getDate().getMonth() == Month.FEBRUARY && bankTransaction.getAmount() >= 1_000);
        System.out.println("transactions: " + transactions);

        LocalDate start = LocalDate.of(2017, 2, 1);
        LocalDate end = LocalDate.of(2017, 2, 3);

        System.out.println("(20170301~20170531) The max for all transactions is " + bankTransactionProcessor.calculateMaxAmount(start, end));

        System.out.println("(20170301~20170531) The min for all transactions is " + bankTransactionProcessor.calculateMinAmount(start, end));

        System.out.println("The histogram with month for all transactions is " + bankTransactionProcessor.calculateHistogramByMonth());

        System.out.println("The histogram with description for all transactions is " + bankTransactionProcessor.calculateHistogramByDescription());
    }
}
