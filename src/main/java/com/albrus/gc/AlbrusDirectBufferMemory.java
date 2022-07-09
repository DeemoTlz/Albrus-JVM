package com.albrus.gc;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class AlbrusDirectBufferMemory {

    /**
     * -Xms10m -Xmx10m -XX:MaxDirectMemorySize=5m
     */
    public static void main(String[] args) {
        System.out.println("Max Direct Memory Size: " + sun.misc.VM.maxDirectMemory() / 1024 / 1024 + "MB.");

        List<ByteBuffer> list = new ArrayList<>();
        while (true) {
            list.add(ByteBuffer.allocateDirect(1 * 1024 * 1024));
        }
    }

}
