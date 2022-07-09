package com.albrus.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class AlbrusReferenceQueue {

    public static void main(String[] args) {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1, referenceQueue);
        System.out.println("o1: " + o1);
        System.out.println("weakReference: " + weakReference.get());
        System.out.println("referenceQueue: " + referenceQueue.poll());

        System.out.println("================ GC ================");
        o1 = null;
        System.gc();

        System.out.println("o1: " + o1);
        System.out.println("weakReference: " + weakReference.get());
        System.out.println("referenceQueue: " + referenceQueue.poll());
    }
}
