package tasks.webapplication.controllers;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@RestController
public class ImageController {

//    @PostMapping("/upload-by-link")
//    public String uploadImageByLink(@RequestBody ImageUploadRequest request) {
//        String link = request.getLink();
//        String newName = "photo" + UUID.randomUUID() + ".jpg";
//
//        try {
//            URL imageUrl = new URL(link);
//            String currentDir = System.getProperty("user.dir");
//            File destinationFile = new File(currentDir + "/src/main/java/uploads/" + newName);
//            FileUtils.copyURLToFile(imageUrl, destinationFile);
//            return newName;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "Error during image upload";
//        }
//    }
@PostMapping("/upload-by-link")
public String uploadImageByLink(@RequestBody ImageUploadRequest request) {
    String link = request.getLink();
    String newName = "photo" + UUID.randomUUID() + ".jpg";

    try {
        URL imageUrl = new URL(link);

        File destinationFile = new File("src/main/resources/static/uploads/" + newName);
        FileUtils.copyURLToFile(imageUrl, destinationFile);
        return newName;
    } catch (IOException e) {
        e.printStackTrace();
        return "Error during image upload";
    }
}

    public static class ImageUploadRequest {
        private String link;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}

