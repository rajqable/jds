import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonFileCreator {
    public static void createJSONFile(String directoryPath, String fileName, JSONObject jsonObject) {

        String filePath = directoryPath + "\\" + fileName + ".json";

        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(jsonObject.toString());
            fileWriter.close();

            System.out.println("JSON file created successfully at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String directoryPath = "C:\\Users\\Unity_0084\\IdeaProjects\\AIRD\\AirdDbFolder";

        String fileName = "Jsonfile12";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Raj");
        jsonObject.put("age", 24);
        jsonObject.put("city", "New York");

        JSONObject parentjsonObject = new JSONObject();
        parentjsonObject.put("Userdetails",jsonObject);

        createJSONFile(directoryPath, fileName, parentjsonObject);
    }
}
