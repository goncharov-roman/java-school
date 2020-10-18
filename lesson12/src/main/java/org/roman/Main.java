package org.roman;

import org.roman.gc.GCClass;
import org.roman.jit.JitClass;

public class Main {

    public static void main(String[] args) {
        JitClass jitClass = new JitClass();
        jitClass.fillMap();

        GCClass gcClass = new GCClass();
        gcClass.createObjects();
    }
}
