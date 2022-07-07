package com.albrus;

public class AlbrusHeapSize {

    public static void main(String[] args) {
        // JVM 内存容量
        long totalMemory = Runtime.getRuntime().totalMemory();
        // JVM 允许使用的最大容量
        long maxMemory = Runtime.getRuntime().maxMemory();

        System.out.println("TOTAL_MEMORY(-Xms) = " + totalMemory + "(字节) " + (totalMemory / 1024 / 1024) + " MB");
        System.out.println("MAX_MEMORY(-Xmx) = " + maxMemory + "(字节) " + (maxMemory / 1024 / 1024) + " MB");
    }

}
