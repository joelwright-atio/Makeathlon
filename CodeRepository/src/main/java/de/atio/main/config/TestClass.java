package de.atio.main.config;

import org.json.JSONObject;

import java.io.InputStream;

public class TestClass {
    private JSONObject content = new JSONObject();

    public InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found: " + fileName);
        } else {
            return inputStream;
        }
    }
}
