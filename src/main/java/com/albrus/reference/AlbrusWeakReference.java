package com.albrus.reference;

import java.lang.ref.WeakReference;

public class AlbrusWeakReference {

    public static void main(String[] args) {
        weakReferenceMemoryEnough();
        System.out.println("=============================");
        weakReferenceMemoryNotEnough();
        System.out.println("=============================");

        ThreadLocal<Object> threadLocal = new ThreadLocal<>();
        threadLocal.set(new Object());

        System.out.println(threadLocal.get());
        System.gc();
        System.out.println(threadLocal.get());
    }

    private static void weakReferenceMemoryEnough() {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println("o1: " + o1);
        System.out.println("weakReference: " + weakReference.get());

        System.out.println("================ GC ================");
        o1 = null;
        System.gc();

        System.out.println("o1: " + o1);
        System.out.println("weakReference: " + weakReference.get());
    }

    /**
     * 故意生成大对象并配置小内存
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    private static void weakReferenceMemoryNotEnough() {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println("o1: " + o1);
        System.out.println("weakReference: " + weakReference.get());

        System.out.println("================ GC ================");
        o1 = null;

        try {
            byte[] bytes = new byte[10 * 1024 * 1024];
        } finally {
            System.out.println("o1: " + o1);
            System.out.println("weakReference: " + weakReference.get());
        }
    }
}
