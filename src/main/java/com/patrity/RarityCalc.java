package com.patrity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrity.model.Attribute;
import com.patrity.model.AttributeType;
import com.patrity.model.Knight;
import com.patrity.model.Rarity;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.patrity.Generator.TOTAL_LAYERS;
import static com.patrity.Main.TOTAL_TO_GENERATE;

@Log
public class RarityCalc {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Path ROOT = Paths.get("metadata");

    @SneakyThrows
    public static void main(String[] args) {
        var allAttributes = new ArrayList<Attribute>();

        try (var paths = Files.walk(ROOT)) {
            paths.filter(Files::isRegularFile).forEach(file -> {
                var knight = readKnight(file);
                allAttributes.addAll(knight.attributes);
            });
        }

        var total = TOTAL_TO_GENERATE * TOTAL_LAYERS;
        if (allAttributes.size() != total) {
            log.info("Incorrect amount of attributes(" + allAttributes.size() + ") should be (" + total + ") " +
                    "amt missing: (" + (total - allAttributes.size()) + ")");
        }

        var attrMapping = new HashMap<AttributeType, Map<Rarity, Integer>>();
        for (var attr : allAttributes) {
            var rarityMapping = attrMapping.computeIfAbsent(attr.type, __ -> new HashMap<>());
            rarityMapping.merge(attr.rarity, 1, Integer::sum);
        }

        attrMapping.forEach((type, rarityMapping) -> {
            for (var rarity : Rarity.VALUES) {
                double probability = (double) rarityMapping.get(rarity) / TOTAL_TO_GENERATE;
                System.out.println(type + " with rarity: " + rarity + " has a chance of " + (probability * 100.0) + "%");
            }
        });
    }

    @SneakyThrows
    private static Knight readKnight(Path path) {
        return objectMapper.readValue(path.toFile(), Knight.class);
    }
}
