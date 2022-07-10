package com.albrus.gc;

import java.util.ArrayList;
import java.util.List;

public class AlbrusGC {

    public static void main(String[] args) throws InterruptedException {
        // -Xms10m -Xmx10m -XX:+PrintGCDetails
        // byte[] bytes = new byte[100 * 1024 * 1024];

        System.out.println("******** Hello GC ********");
        // Thread.sleep(Integer.MAX_VALUE);

        List<Object> list = new ArrayList<>(100);
        for (int i = 0; i < 10; i++) {
            list.add(new byte[1 * 1024 * 1024]);
        }
    }

}
