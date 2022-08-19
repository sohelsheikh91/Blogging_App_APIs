package com.springboot.blog.services.impl;

import com.springboot.blog.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
//Check Applications.properties

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        String name = file.getOriginalFilename();

        //Random Name Generator
        String randomID = UUID.randomUUID().toString();
        name = randomID.concat(name.substring(name.lastIndexOf(".")));

        //Generate File Path with Name
        String filePath = path + File.separator + name;

        //create folder if not created
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }
        //file Copy
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return name;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {

        String filePath = path + File.separator + fileName;
        InputStream is = new FileInputStream(filePath);
        return is;
    }
}
