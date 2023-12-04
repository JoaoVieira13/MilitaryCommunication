package com.example.JSON;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SaveJsonToFile {
    public static void saveJsonToFile(List<?> list, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(list, writer);
            //System.out.println("Registros salvos em " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String convertObjectToJson(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }

    public static <T> List<T> loadObjects(String fileName, Type type) {
        try (FileReader reader = new FileReader(fileName)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
