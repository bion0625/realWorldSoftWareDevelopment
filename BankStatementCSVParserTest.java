import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BankStatementCSVParserTest {
    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() {
//        Assert.fail("Not yet implement");
        final String line = "30-01-2017,-50,Tesco";

        BankTransaction result = statementParser.parseFrom(line);

        final BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");
        final double tolerance = 0.0d;

        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        Assert.assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void shiuldParseTwoCorrectLine() {
        // given
        final String str = "30-01-2017,-100,Deliveroo\n30-01-2017,-50,Tesco";

        // when
        List<String> lines = Arrays.stream(str.split("\n")).collect(Collectors.toList());
        List<BankTransaction> result = statementParser.parseLinesFrom(lines);

        List<BankTransaction> expected = List.of(
                new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -100, "Deliveroo"),
                new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco")
        );

        final double tolerance = 0.0d;

        // then
        Assert.assertEquals(result.size(), expected.size());
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(expected.get(i).getDate(), result.get(i).getDate());
            Assert.assertEquals(expected.get(i).getAmount(), result.get(i).getAmount(), tolerance);
            Assert.assertEquals(expected.get(i).getDescription(), result.get(i).getDescription());
        }
    }
}