package de.atio.main.config;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;

public class ConfigFileHandler {
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

    public ConfigFileHandler(String filePath) throws Exception {
        InputStream inputStream = getFileFromResourceAsStream(filePath);
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new InputStreamReader(inputStream, "UTF-8"));
        this.content = new JSONObject(obj.toString());
    }

    public String GetStringValue(String path) throws Exception {
        JSONObject json = new JSONObject(this.content, JSONObject.getNames(this.content));
        String[] parts = path.split(",");
        for (int x = 0; x < parts.length; x++) {
            Object obj = json.get(parts[x]);
            if (obj instanceof JSONObject) {
                json = json.getJSONObject(parts[x]);
            } else {
                return json.getString(parts[x]);
            }
        }
        throw new Error("No string value found. Please verify path.");
    }

    public Boolean GetBooleanValue(String path) throws Exception {
        JSONObject json = new JSONObject(this.content, JSONObject.getNames(this.content));
        String[] parts = path.split(",");
        for (int x = 0; x < parts.length; x++) {
            Object obj = json.get(parts[x]);
            if (obj instanceof JSONObject) {
                json = json.getJSONObject(parts[x]);
            } else {
                return json.getBoolean(parts[x]);
            }
        }
        throw new Error("No boolean value found. Please verify path.");
    }
}
