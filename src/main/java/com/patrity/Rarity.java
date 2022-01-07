package com.patrity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Rarity {

    A("a", 50),
    B("b", 25),
    C("c", 15),
    D("d", 6),
    E("e", 3),
    F("f", 1);

    private static final Rarity[] VALUES = values();

    public String prefix;
    public int weight;

    private static int sumOfAll() {
        int total = 0;
        for (Rarity rarity : VALUES) {
            total += rarity.weight;
        }
        return total;
    }

    public static Rarity getRandomRarity() {
        int totalWeight = sumOfAll();
        double random = Utils.random.nextDouble() * totalWeight;
        for (Rarity rarity : VALUES) {
            if ((random -= rarity.weight) <= 0) {
                return rarity;
            }
        }
        return Rarity.A;
    }

}
