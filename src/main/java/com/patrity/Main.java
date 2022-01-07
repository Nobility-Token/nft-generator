package com.patrity;

public class Main {


    public Main() {
        final Generator generator = new Generator();
        final int totalToGenerate = 5;
        long startTime = System.currentTimeMillis();
        long lastTime;
        for (int i = 1; i <= totalToGenerate; i++) {
            lastTime = System.currentTimeMillis();
            generator.createNFT(i);
            long timeTaken = System.currentTimeMillis() - lastTime;
            long currentTime = (System.currentTimeMillis() - startTime) / 1000;
            System.out.println("Generated " + i + "/" + totalToGenerate + " in " + currentTime + "s (" + timeTaken + "ms)");
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
