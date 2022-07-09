package com.albrus.gc;

import java.util.ArrayList;
import java.util.List;

public class AlbrusOverHeadGC {

    /**
     * -Xms10m -Xmx10m -XX:+PrintGCDetails
     */
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();

        while (!Thread.currentThread().isInterrupted()) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
