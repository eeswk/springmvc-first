package ee.swan.controller;

import ee.swan.service.UploadService;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class AsyncUploader {

    @Autowired
    UploadService uploadService;

    @Async
    public CompletableFuture<String> upload(MultipartFile file) {

        uploadService.upload(file); //별도의 스레드에 실행할 만큼 무거운 처리

        return CompletableFuture.completedFuture("upload/complete");
    }
}
