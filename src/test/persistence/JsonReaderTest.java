package persistence;

import model.Categories;
import model.Item;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for JsonReader class
 */

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ToDoList toDoList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyToDoList.json");
        try {
            ToDoList toDoList = reader.read();
            assertEquals("Anie's To-Do List", toDoList.getName());
            assertEquals(0, toDoList.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralToDoList.json");
        try {
            ToDoList toDoList = reader.read();
            assertEquals("Anie's To-Do List", toDoList.getName());
            assertEquals(2, toDoList.getSize());
            checkItem("item 1", "2", Categories.HIGHPRIORITY, toDoList.getItemAtIndex(1));
            checkItem("item 2", "3", Categories.MIDPRIORITY, toDoList.getItemAtIndex(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}