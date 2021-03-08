package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for ToDoList class
 */

public class ToDoListTest {

    private ToDoList toDoList;
    private Item item;
    private Item item1;

    @BeforeEach
    public void runBefore() {
        toDoList = new ToDoList("name");
        item = new Item("test", "2", Categories.HIGHPRIORITY);
        item1 = new Item("test1", "1", Categories.LOWPRIORITY);
    }

    @Test
    public void testInsert() {
        assertEquals(toDoList.getSize(), 0);
        toDoList.insert(item);
        assertEquals(toDoList.getSize(), 1);
        assertTrue(toDoList.contains(item));
    }

    @Test
    public void testInsertMultiple() {
        testInsert();
        toDoList.insert(item1);
        assertEquals(toDoList.getSize(), 2);
        assertTrue(toDoList.contains(item1));
    }

    @Test
    public void testRemove() {
        testInsertMultiple();
        toDoList.remove(2);
        assertEquals(toDoList.getSize(), 1);
        assertTrue(toDoList.contains(item));
        assertFalse(toDoList.contains(item1));
    }

    @Test
    public void testRemoveMultiple() {
        testRemove();
        toDoList.remove(1);
        assertEquals(toDoList.getSize(), 0);
        assertFalse(toDoList.contains(item));
        assertFalse(toDoList.contains(item1));
    }

    @Test
    public void testRemoveInvalid() {
        toDoList.insert(item);
        toDoList.remove(2);
        assertEquals(toDoList.getSize(), 1);
    }

    @Test
    public void testSort() {
        toDoList.insert(item);
        toDoList.insert(item1);
        toDoList.sortByDueDate();
        toDoList.sortByCategory();
        assertEquals(toDoList.returnIndex(item1), 2);
        assertEquals(toDoList.returnIndex(item), 1);
    }

    @Test
    public void testGetItemAtIndex() {
        toDoList.insert(item);
        assertEquals(toDoList.getItemAtIndex(1), item);
    }

    @Test
    public void testReturnIndexInvalid() {
        toDoList.insert(item);
        assertEquals(toDoList.returnIndex(item1), -1);
    }

    @Test
    public void testLastIndexOfCategory() {
        testInsertMultiple();
        int lastIndex = toDoList.lastIndexOfCategory(Categories.LOWPRIORITY);
        assertEquals(1, lastIndex);
    }

    @Test
    public void testLastIndexOfCategoryNotPresent() {
        testInsertMultiple();
        int lastIndex = toDoList.lastIndexOfCategory(Categories.MIDPRIORITY);
        assertEquals(0, lastIndex);
    }
}
