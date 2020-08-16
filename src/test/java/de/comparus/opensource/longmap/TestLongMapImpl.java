package de.comparus.opensource.longmap;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TestLongMapImpl {

    private LongMapImpl<String> longMap;
    private Map<Long,String> map;

    @Before
    public void initTest() {
        longMap = new LongMapImpl<>();
        map = new HashMap<>();
    }

    @Test
    public void testPut() {
        LongMapImpl<String> longMap = new LongMapImpl<>();
        for(int i = 0; i <= 10; i++) {
            longMap.put(1, "Hello_" + i);
            longMap.put(2, "Hello_" + i);
            longMap.put(3, "Hello_" + i);
        }
        System.out.println(Arrays.toString(longMap.keys()));
        System.out.println(Arrays.toString(longMap.values()));
        System.out.println(longMap.toString());
    }

    @Test
    public void testGet() {
        LongMapImpl<Integer> longMap = new LongMapImpl<>();
        longMap.put(0, 100);

        System.out.println(longMap.get(0));
    }

    @Test
    public void testRemove() {
        System.out.println(longMap.remove(200));
        System.out.println(longMap.remove(200));
    }

    @Test
    public void testIsEmpty() {
        LongMap<Integer> longMap_1 = new LongMapImpl<>();
        System.out.println(longMap.isEmpty());
        System.out.println(longMap_1.isEmpty());
    }

    @Test
    public void testContainsKey() {
        System.out.println(longMap.containsKey(100000000));
    }

    @Test
    public void testClear() {
        System.out.println(longMap.toString());
        longMap.clear();
        System.out.println(longMap.size());

    }

    @Test
    public void testLongMapTiming() {
        System.out.println("----------------------- put timestamp -----------------------");
        long timeStartLongMapPut = System.currentTimeMillis();
        for(int i = 1; i < 100000; i++) {
            longMap.put(i, "TestElement_" + i);
        }
        long timePutElementLongMap = System.currentTimeMillis() - timeStartLongMapPut;
        System.out.println("LongMap put method time: " + timePutElementLongMap);

        long timeStartMapPut = System.currentTimeMillis();
        for(int i = 1; i < 100000; i++) {
            map.put((long) i, "TestElement_" + i);
        }
        long timePutElementMap = System.currentTimeMillis() - timeStartMapPut;
        System.out.println("Map put method time: " + timePutElementMap);
        System.out.println("-------------------------------------------------------------");
        System.out.println("-----------------------get timestamp-------------------------");
        long timeStartLongMapGet = System.currentTimeMillis();
        longMap.get(9000);
        long timeGetElementLongMap = System.currentTimeMillis() - timeStartLongMapGet;
        System.out.println("LongMap get method time: " + timeGetElementLongMap);
        long timeStartMapGet = System.currentTimeMillis();
        map.get(9000L);
        long timeGetElementMap = System.currentTimeMillis() - timeStartMapGet;
        System.out.println("Map get method time: " + timeGetElementMap);

    }
}