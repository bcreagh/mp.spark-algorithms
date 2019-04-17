package com.bcreagh.mpspark.services;

import com.bcreagh.mpspark.mp.domain.Config;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigService {

    private static Config config;

    public static void init() throws IOException {
        try {
            String actionsJson = FileService.readFileFromResources("config.json", "UTF-8");
            Gson gson = new Gson();
            config = gson.fromJson(actionsJson, Config.class);
        } catch (JsonParseException ex) {
            System.out.println("There was a problem parsing the config.json file");
            throw ex;
        }
    }

    public static int getPort() {
        return config.getPort();
    }

    public static String getServiceName() {
        return config.getServiceName();
    }
}
