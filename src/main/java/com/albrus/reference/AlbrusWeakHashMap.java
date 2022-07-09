package com.albrus.reference;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class AlbrusWeakHashMap {

    public static void main(String[] args) {
        hashMap();
        System.out.println("----------------  ----------------");
        weakHashMap();
    }

    private static void hashMap() {
        Map<Object, String> map = new HashMap<>();
        Object key = new Object();
        map.put(key, "A");
        System.out.println(map);

        System.out.println("================ GC ================");

        key = null;
        System.gc();
        System.out.println(map);
    }

    private static void weakHashMap() {
        Map<Object, String> map = new WeakHashMap<>();
        Object key = new Object();
        map.put(key, "A");
        System.out.println(map);

        System.out.println("================ GC ================");

        key = null;
        System.gc();
        System.out.println(map);
    }
}
