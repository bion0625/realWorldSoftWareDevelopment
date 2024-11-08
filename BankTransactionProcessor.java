import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BankTransactionProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankTransactionProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {
        double total = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public double calculateTotalInMonth(final Month month) {
        double total = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    public double calculateTotalForCategory(final String category) {
        double total = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDescription().equals(category)) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    public double calculateMaxAmount(final LocalDate start, final LocalDate end) {
        return getByPeriod(start, end)
                .mapToDouble(BankTransaction::getAmount)
                .max().orElse(0.0);
    }

    public double calculateMinAmount(final LocalDate start, final LocalDate end) {
        return getByPeriod(start, end)
                .mapToDouble(BankTransaction::getAmount)
                .max().orElse(0.0);
    }

    private Stream<BankTransaction> getByPeriod(final LocalDate start, final LocalDate end) {
        return bankTransactions.stream()
                .filter(bankTransaction -> bankTransaction.getDate().isAfter(start) && bankTransaction.getDate().isBefore(end));
    }

    public Map<Month, Double> calculateHistogramByMonth() {
        return bankTransactions.stream()
                .collect(
                        Collectors.groupingBy(bankTransaction -> bankTransaction.getDate().getMonth(),
                                Collectors.summingDouble(BankTransaction::getAmount)));
    }

    public Map<String, Double> calculateHistogramByDescription() {
        return bankTransactions.stream()
                .collect(
                        Collectors.groupingBy(BankTransaction::getDescription,
                                Collectors.summingDouble(BankTransaction::getAmount)));
    }
}
