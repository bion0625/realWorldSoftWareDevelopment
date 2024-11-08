import java.util.HashMap;
import java.util.Map;

public class DocumentmanagementSystem {
    private final Map<String, Importer> extensionToImporter = new HashMap<>();

    public DocumentmanagementSystem() {
        extensionToImporter.put("letter", new LetterImporter());
        extensionToImporter.put("report", new ReportImporter());
        extensionToImporter.put("jpg", new ImageImporter());
    }
}
