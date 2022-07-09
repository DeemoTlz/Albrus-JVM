package com.albrus.gc;

public class AlbrusGC {

    public static void main(String[] args) throws InterruptedException {
        // -Xms10m -Xmx10m -XX:+PrintGCDetails
        // byte[] bytes = new byte[100 * 1024 * 1024];

        System.out.println("******** Hello GC ********");
        // Thread.sleep(Integer.MAX_VALUE);
    }

}
