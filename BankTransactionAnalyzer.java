import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankTransactionAnalyzer {
    private static final String RESOURCES = "src/main/resources";

    public static void main(String[] args) throws IOException {
        final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

        final String fileName = args[0];
        final Path path = Paths.get(RESOURCES + fileName);
        List<String> lines = Files.readAllLines(path);

        List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFromCSV(lines);
        BankTransactionProcessor bankTransactionProcessor = new BankTransactionProcessor(bankTransactions);

        collectSummary(bankTransactionProcessor);
    }

    private static void collectSummary(final BankTransactionProcessor bankTransactionProcessor) {
        System.out.println("The total for all transactions is " + bankTransactionProcessor.calculateTotalAmount());

        System.out.println("The total for transactions in January " + bankTransactionProcessor.calculateTotalInMonth(Month.JANUARY));

        System.out.println("The total for transactions in february " + bankTransactionProcessor.calculateTotalInMonth(Month.FEBRUARY));

        System.out.println("The total salary received is " + bankTransactionProcessor.calculateTotalForCategory("Salary"));
    }
}
