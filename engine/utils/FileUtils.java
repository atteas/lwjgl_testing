package engine.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {
    public static String loadAsString(String path){
        StringBuilder result = new StringBuilder();

        InputStream stream = FileUtils.class.getClassLoader().getResourceAsStream(path);
        if (stream == null){
            System.err.println("Error: shader not found: " + path);
            return "";
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = reader.readLine()) != null){
                result.append(line).append("\n");
            }
        } catch (IOException e){
            System.err.println("Couldn't find the file at " + path);
        }
        return result.toString();
    }
}