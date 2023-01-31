package pl.edu.pjwstk.snl.config.dataloader;

import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ImageUtil {
    static byte[] getBytes(Resource resource) {
        byte[] fileContent = new byte[0];
        File file;

        try {
            file = resource.getFile();
            fileContent = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }
}