package Core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AirdDatabase {
    String databaseName = "";

    public void createDatabase(String databaseName) {
        this.databaseName = databaseName;

        System.out.println(databaseName);
        //FILE CREATE
        //FILE NAME SHOULD BE DATABASENAME

        String filePath = "src/test/java/data/" + databaseName + ".json";
        String dirPath1 = "src/test/java/data";
        Path dirPath = Paths.get(dirPath1);
        if (!Files.exists(dirPath)) {
            try {
                Files.createDirectory(dirPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.close();

            System.out.println("\nJSON file created successfully at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing JSON to file: " + e.getMessage());
        }

    }

    public void createCollection(String collectionName) {

        JSONObject mainJsonObject = new JSONObject();

        String filePath = "src/test/java/data/" + databaseName + ".json";

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            if (line != null && !line.isEmpty()) {
                mainJsonObject = new JSONObject(line);
            }
            JSONArray jsonArray = mainJsonObject.optJSONArray(collectionName);
            if (jsonArray == null) {
                jsonArray = new JSONArray();
                mainJsonObject.put(collectionName, jsonArray);
            }

            FileWriter file = new FileWriter(filePath);
            file.write(mainJsonObject.toString());
            file.close();
            System.out.println("\nCollection " + collectionName + " created successfully in " + databaseName + " json file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing JSON to file: " + e.getMessage());
        }

    }

    public void addRecords(String collectionName, String type, String userType) {

        String filePath = "src/test/java/data/" + databaseName + ".json";

        try {
            // Read existing JSON data from the file
            StringBuilder jsonDataBuilder = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    jsonDataBuilder.append(line).append("\n");
                }
            }

            // Parse the existing JSON data
            JSONObject mainJsonObject;
            if (jsonDataBuilder.length() > 0) {
                mainJsonObject = new JSONObject(jsonDataBuilder.toString());
            } else {
                mainJsonObject = new JSONObject();
            }

            // Get the JSONArray for the collection name or create a new one if it doesn't exist
            JSONArray jsonArray = mainJsonObject.optJSONArray(collectionName);
            if (jsonArray == null) {
                jsonArray = new JSONArray();
                mainJsonObject.put(collectionName, jsonArray);
            }

            // Create a JSONObject for the new record and add it to the JSONArray
            JSONObject record = new JSONObject();
            record.put(type, userType);
            jsonArray.put(record);


            // Write the updated JSON data back to the file
            try (FileWriter file = new FileWriter(filePath)) {
                file.write(mainJsonObject.toString());
            }

            System.out.println("\nRecord added successfully to collection " + collectionName + " in " + databaseName + " JSON file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing JSON to file: " + e.getMessage());
        }
    }



}






