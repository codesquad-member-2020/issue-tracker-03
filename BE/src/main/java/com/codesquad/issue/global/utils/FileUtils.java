package com.codesquad.issue.global.utils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

    private static boolean convert(MultipartFile multipartFile) {
        if (multipartFile != null && multipartFile.getSize() > 0
                && multipartFile.getOriginalFilename() != null) {
            return true;
        }
        return false;
    }

    public static File toFile(MultipartFile multipartFile) throws IOException {
        if (convert(multipartFile)) {
            File convertFile = new File(multipartFile.getOriginalFilename());
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(multipartFile.getBytes());
            }
            return convertFile;
        }
        return null;
    }
}
