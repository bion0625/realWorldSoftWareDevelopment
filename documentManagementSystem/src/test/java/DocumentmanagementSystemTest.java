import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class DocumentmanagementSystemTest {

    static final String RESOURCES = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    static final String LETTER = RESOURCES + "patient.letter";
    static final String REPORT = RESOURCES + "patient.report";
    static final String XRAY = RESOURCES + "xray.jpg";
    static final String INVOICE = RESOURCES + "patient.invoice";
    static final String JOE_BLOGGS = "Joe Bloggs";
    static final String PATIENT = "patient";
    static final String ADDRESS = "address";
    static final String BODY = "body";
    static final String WIDTH = "width";
    static final String HEIGHT = "height";
    static final String TYPE = "type";


    DocumentmanagementSystem system = new DocumentmanagementSystem();

    @Test
    public void shouldImportFile() {
        system.importFile(LETTER);

        final Document document = onlyDocument();

        assertAttributeEquals(document, PATIENT, JOE_BLOGGS);
        assertAttributeEquals(document, ADDRESS,
                "123 Fake Street]n" +
                        "Westminster\n" +
                        "London\n" +
                        "United Kingdom");
        assertAttributeEquals(document, BODY,
                "We are writing to you to confirm the re-scheduling of your appointment\n" +
                        "With Dr. Avaj from 29th December 2016 to 5th January 2017.");
        assertTypeIs("LETTER", document);
    }

    @Test
    public void shouldImportImageAttributes() {
        system.importFile(XRAY);

        final Document document = onlyDocument();

        assertAttributeEquals(document, WIDTH, "320");
        assertAttributeEquals(document, HEIGHT, "179");
        assertTypeIs("IMAGE", document);
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldNotImportMissingFile() {
        system.importFile("gobbledygook.txt");
    }

    @Test(expected = UnKnownFileTypeException.class)
    public void shouldNotImportUnknownFile() {
        system.importFile(RESOURCES + "unknown.txt");
    }

    private void assertAttributeEquals(
            final Document document,
            final String attributeName,
            final String expectedValue
    ) {
        Assert.assertEquals(
                "Document has the wrong value for " + attributeName,
                expectedValue,
                document.getAttribute(attributeName)
        );
    }

    private Document onlyDocument() {
        final List<Document> documents = system.contents();
        Assert.assertThat(documents, Matchers.hasSize(1));
        return documents.get(0);
    }

    private void assertTypeIs(final String type, final Document document) {
        Assert.assertEquals(type, document.getAttribute(TYPE));
    }

}