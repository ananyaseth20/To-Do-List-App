package persistence;

import model.Categories;
import model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkItem(String name, String daysBeforeDue, Categories category, Item item) {
        assertEquals(name, item.getTitle());
        assertEquals(daysBeforeDue, item.getDaysBeforeDue());
        assertEquals(category, item.getCategory());
    }
}
