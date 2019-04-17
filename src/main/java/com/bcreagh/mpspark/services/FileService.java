package com.bcreagh.mpspark.services;

import java.io.IOException;
import java.io.InputStream;

public class FileService {

    public static String readFileFromResources(String fileName, String encoding) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(fileName);
        byte[] fileAsBytes = inputStream.readAllBytes();
        String fileAsString = new String(fileAsBytes, encoding);
        return fileAsString;
    }

}
