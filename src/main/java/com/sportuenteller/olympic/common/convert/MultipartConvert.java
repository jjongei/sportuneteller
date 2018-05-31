package com.sportuenteller.olympic.common.convert;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultipartConvert {

    public static File convertFirst(MultipartFile[] mFiles) throws IOException {
        List<File> files = convert(mFiles);
        return files.stream().findFirst().orElse(null);
    }

    public static List<File> convert(MultipartFile[] mFiles) throws IOException {
        List<File> files = new ArrayList<>();

        if(mFiles == null || mFiles.length == 0){
            return Collections.emptyList();
        }

        for(MultipartFile mFile : mFiles){
            String fileName = mFile.getOriginalFilename();
            File file = new File(Paths.get("").toAbsolutePath().toString() +File.separator+ "["+Long.toString(System.nanoTime()) +"]"+ fileName);
            try {
                byte[] bytes =mFile.getBytes();
                BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(file));
                buffStream.write(bytes);
                buffStream.close();
            } catch (IOException e) {
                throw  e;
            }
            if(file != null && file.exists()){
                files.add(file);
            }
        }
        return files;
    }
}
