import java.util.Map;

public class Document {
    private final Map<String, String> attributes;

    Document(Map<String, String> attribute) {
        this.attributes = attribute;
    }

    public String getAttribute(final String attributeName) {
        return attributes.get(attributeName);
    }
}
