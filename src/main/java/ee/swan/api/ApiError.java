package ee.swan.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class ApiError implements Serializable {
    private static final long serialVersionUID = -170820078713306637L;

    private String message;

    @JsonProperty("documentation_url")
    private String documentationUrl;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocumentationUrl() {
        return documentationUrl;
    }

    public void setDocumentationUrl(String documentationUrl) {
        this.documentationUrl = documentationUrl;
    }
}
