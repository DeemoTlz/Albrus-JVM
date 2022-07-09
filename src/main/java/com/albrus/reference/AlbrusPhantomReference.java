package com.albrus.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class AlbrusPhantomReference {

    public static void main(String[] args) {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1, referenceQueue);
        System.out.println("o1: " + o1);
        System.out.println("phantomReference: " + phantomReference.get());
        System.out.println("referenceQueue: " + referenceQueue.poll());

        System.out.println("================ GC ================");
        o1 = null;
        System.gc();

        System.out.println("o1: " + o1);
        System.out.println("phantomReference: " + phantomReference.get());
        System.out.println("referenceQueue: " + referenceQueue.poll());
    }

}
