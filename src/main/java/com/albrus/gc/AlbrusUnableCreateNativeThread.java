package com.albrus.gc;

public class AlbrusUnableCreateNativeThread {

    public static void main(String[] args) {
        for (int i = 0; ; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, i + "").start();
        }
    }

}
