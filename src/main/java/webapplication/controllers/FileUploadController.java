package webapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class FileUploadController {


    private String uploadDir = "src/main/resources/static/uploads/";



    @PostMapping("/upload")
    public ResponseEntity<List<String>> handleFileUpload(
            @RequestParam("photos") MultipartFile[] photos) {

        List<String> fileNames = new ArrayList<>();

        for (MultipartFile photo : photos) {
            if (photo != null && !photo.isEmpty()) {
                String originalFilename = photo.getOriginalFilename();
                String newFileName = "photo" + UUID.randomUUID() + getFileExtension(originalFilename);

                try {
                    String currentDir = System.getProperty("user.dir");
                    File destinationFile = new File(currentDir + "/src/main/resources/static/uploads/" + newFileName);
                    photo.transferTo(destinationFile);
                    fileNames.add(newFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                    return ResponseEntity.badRequest().body(null);
                }
            }
        }

        return ResponseEntity.ok(fileNames);
    }


    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex != -1) {
            return fileName.substring(lastDotIndex);
        }
        return "";
    }

}

