package com.iteratrlearning;

@FunctionalInterface
public interface BankTransactionSummarizer {
    double summarizer(double accumulator, BankTransaction bankTransaction);
}
