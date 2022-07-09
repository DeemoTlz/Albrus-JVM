package com.albrus.reference;

import java.lang.ref.SoftReference;

public class AlbrusSoftReference {

    public static void main(String[] args) {
        softReferenceMemoryEnough();
        System.out.println("=============================");
        softReferenceMemoryNotEnough();
    }

    private static void softReferenceMemoryEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println("o1: " + o1);
        System.out.println("softReference: " + softReference.get());

        System.out.println("================ GC ================");
        o1 = null;
        System.gc();

        System.out.println("o1: " + o1);
        System.out.println("softReference: " + softReference.get());
    }

    /**
     * 故意生成大对象并配置小内存
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    private static void softReferenceMemoryNotEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println("o1: " + o1);
        System.out.println("softReference: " + softReference.get());

        System.out.println("================ GC ================");
        o1 = null;

        try {
            byte[] bytes = new byte[10 * 1024 * 1024];
        } finally {
            System.out.println("o1: " + o1);
            System.out.println("softReference: " + softReference.get());
        }
    }
}
