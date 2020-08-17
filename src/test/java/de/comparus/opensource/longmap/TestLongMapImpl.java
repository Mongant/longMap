package de.comparus.opensource.longmap;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestLongMapImpl {

    @Test
    public void testPut() {
        System.out.println("----------------------- put timestamp -----------------------");
        long start = System.currentTimeMillis();
        LongMapImpl<String> longMap = new LongMapImpl<>();
        for(int i = 0; i < 100000; i++) {
            longMap.put(i, "TestValue_" + i);
        }
        long timestamp = System.currentTimeMillis() - start;
        System.out.println("LongMap put method timestamp: " + timestamp);
        System.out.println("-------------------------------------------------------------");
        assertEquals(100000, longMap.size());
    }

    @Test
    public void testGet() {
        System.out.println("----------------------- get timestamp -----------------------");
        LongMapImpl<String> longMap = new LongMapImpl<>();
        for(int i = 0; i < 100000; i++) {
            longMap.put(i, "TestValue_" + i);
        }
        long start = System.currentTimeMillis();
        assertEquals("TestValue_10000", longMap.get(10000));
        long timestamp = System.currentTimeMillis() - start;
        System.out.println("LongMap get method timestamp: " + timestamp);
        System.out.println("-------------------------------------------------------------");
    }

    @Test
    public void testRemove() {
        System.out.println("-----------------------remove timestamp----------------------");
        int[] removeKeys = new int[1000];

        LongMapImpl<String> longMap = new LongMapImpl<>();
        for(int i = 0; i < 100000; i++) {
            longMap.put(i, "TestValue_" + i);
            if(i < 1000) {
                removeKeys[i] = i;
            }
        }

        long start = System.currentTimeMillis();
        for (int n : removeKeys) {
            longMap.remove(n);
        }

        long timestamp = System.currentTimeMillis() - start;
        System.out.println("LongMap remove method timestamp: " + timestamp);
        System.out.println("-------------------------------------------------------------");

        assertFalse(longMap.containsKey(200));
        assertFalse(longMap.containsKey(500));
        assertTrue(longMap.containsKey(5000));
        assertEquals(99000, longMap.size());
    }

    @Test
    public void testIsEmpty() {
        LongMap<String> longMap = new LongMapImpl<>();

        for(int i = 0; i < 100000; i++) {
            longMap.put(i, "TestValue_" + i);
        }

        assertFalse(longMap.isEmpty());

        longMap.clear();

        assertTrue(longMap.isEmpty());
    }

    @Test
    public void testContainsKey() {
        System.out.println("-------------------containsKey timestamp---------------------");
        LongMap<String> longMap = new LongMapImpl<>();

        for(int i = 0; i < 1000; i++) {
            longMap.put(i, "TestValue_" + i);
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            longMap.containsKey(i);
        }
        long timestamp = System.currentTimeMillis() - start;
        System.out.println("LongMap containsKey method timestamp: " + timestamp);
        System.out.println("-------------------------------------------------------------");

        assertTrue(longMap.containsKey(500));
        assertFalse(longMap.containsKey(-500));
    }

    @Test
    public void testContainsValue() {
        System.out.println("-------------------containsValue timestamp---------------------");
        LongMap<String> longMap = new LongMapImpl<>();

        for(int i = 0; i < 1000; i++) {
            longMap.put(i, "TestValue_" + i);
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            longMap.containsValue("TestValue_" + i);
        }
        long timestamp = System.currentTimeMillis() - start;
        System.out.println("LongMap containsValue method timestamp: " + timestamp);
        System.out.println("-------------------------------------------------------------");

        assertTrue(longMap.containsValue("TestValue_500"));
        assertFalse(longMap.containsValue("TestValue_-500"));
    }

    @Test
    public void testClear() {
        LongMap<String> longMap = new LongMapImpl<>();

        for(int i = 0; i < 1000; i++) {
            longMap.put(i, "TestValue_" + i);
        }
        longMap.clear();
        assertEquals(0,longMap.size());
    }
}