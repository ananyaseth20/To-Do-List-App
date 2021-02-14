package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Categories.*;
import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for Item class
 */

class ItemTest {

    private Item i;

    @BeforeEach
    public void runBefore() {
        i = new Item("test", "1", HighPriority);
    }

    @Test
    public void testGetName() {
        assertEquals(i.getName(), "test");
    }

    @Test
    public void testGetDaysBeforeDue() {
        assertEquals(i.getDaysBeforeDue(), "1");
    }

    @Test
    public void testGetCategory() {
        assertEquals(i.getCategory(), HighPriority);
    }
}