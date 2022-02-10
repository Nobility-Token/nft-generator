package com.patrity;

public class Main {
    public static final int TOTAL_TO_GENERATE = 4444;

    public Main() {
        final Generator generator = new Generator();
        long startTime = System.currentTimeMillis();
        long lastTime;
        for (int i = 1; i <= TOTAL_TO_GENERATE; i++) {
            lastTime = System.currentTimeMillis();
            generator.createNFT(i);
            long timeTaken = System.currentTimeMillis() - lastTime;
            long currentTime = (System.currentTimeMillis() - startTime) / 1000;
            System.out.println("Generated " + i + "/" + TOTAL_TO_GENERATE + " in " + currentTime + "s (" + timeTaken + "ms)");
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
