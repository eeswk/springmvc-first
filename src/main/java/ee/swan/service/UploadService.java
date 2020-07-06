package ee.swan.service;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

    public void upload(MultipartFile file) {

        String contentType = file.getContentType();
        String parameterName = file.getName();
        String originalFilename = file.getOriginalFilename();
        long fileSize = file.getSize();

        try (InputStream content = file.getInputStream()) {
            //업로드된 데이터를 저장
        } catch (IOException e) {

        }

    }
}
