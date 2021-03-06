package ee.swan.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file/upload")
public class FileUploadController {

    @Autowired
    AsyncUploader asyncUploader;

    @RequestMapping(path = "upload", method = RequestMethod.POST)
    public CompletableFuture<String> upload(MultipartFile file) {
        return asyncUploader.upload(file); //비동기 처리를 호출
    }

    @RequestMapping(method = RequestMethod.POST)
    public String upload(FileUploadForm form) {

        MultipartFile file = form.getFile();

        String contentType = file.getContentType();
        String parameterName = file.getName();
        String originalFilename = file.getOriginalFilename();
        long fileSize = file.getSize();

        try (InputStream content = file.getInputStream()) {
            //업로드된 데이터를 저장
        } catch (IOException e) {

        }

        return "redirect:/file/upload?complete";


    }
}
