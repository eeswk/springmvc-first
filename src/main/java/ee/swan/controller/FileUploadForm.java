package ee.swan.controller;

import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadForm implements Serializable {
    private static final long serialVersionUID = 203228004056812683L;

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
