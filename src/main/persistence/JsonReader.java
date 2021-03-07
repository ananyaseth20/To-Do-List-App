package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

/*
 * Represents a reader that reads workroom from JSON data stored in file
 */
// source: JSONSerializationDemo

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ToDoList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToDoList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ToDoList parseToDoList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ToDoList toDoList = new ToDoList(name);
        addToFile(toDoList, jsonObject);
        return toDoList;
    }

    // MODIFIES: toDoList
    // EFFECTS: parses items from JSON object and adds them to to-do list
    private void addToFile(ToDoList toDoList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("toDoList");
        for (Object json : jsonArray) {
            JSONObject jsonObject1 = (JSONObject) json;
            addItem(toDoList, jsonObject1);
        }
    }

    // MODIFIES: toDoList
    // EFFECTS: parses item from JSON object and adds it to to-do list
    private void addItem(ToDoList toDoList, JSONObject jsonObject) {
        String title = jsonObject.getString("name");
        String daysBeforeDue = jsonObject.getString("daysBeforeDue");
        Categories category = Categories.valueOf(jsonObject.getString("category"));
        Item item = new Item(title, daysBeforeDue, category);
        toDoList.insert(item);
    }
}
