package persistence;

import model.Categories;
import model.Item;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for JsonWriter class
 */

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ToDoList toDoList = new ToDoList("Anie's To-Do List");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ToDoList toDoList = new ToDoList("Anie's To-Do List");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyToDoList.json");
            writer.open();
            writer.write(toDoList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyToDoList.json");
            toDoList = reader.read();
            assertEquals("Anie's To-Do List", toDoList.getName());
            assertEquals(0, toDoList.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ToDoList toDoList = new ToDoList("Anie's To-Do List");
            toDoList.insert(new Item("item 1", "2", Categories.HIGHPRIORITY));
            toDoList.insert(new Item("item 2", "3", Categories.MIDPRIORITY));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralToDoList.json");
            writer.open();
            writer.write(toDoList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralToDoList.json");
            toDoList = reader.read();
            assertEquals("Anie's To-Do List", toDoList.getName());
            assertEquals(2, toDoList.getSize());
            checkItem("item 1", "2", Categories.HIGHPRIORITY, toDoList.getItemAtIndex(1));
            checkItem("item 2", "3", Categories.MIDPRIORITY, toDoList.getItemAtIndex(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}