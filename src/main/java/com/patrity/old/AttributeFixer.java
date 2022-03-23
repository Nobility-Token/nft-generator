package com.patrity.old;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrity.model.Attribute;
import com.patrity.model.Knight;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;

@Log
public class AttributeFixer {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Path ROOT = Paths.get("metadata");

    @SneakyThrows
    public static void main(String[] args) {
        if (Files.notExists(ROOT)) {
            throw new IllegalStateException("Cannot update metadata that does not exist!");
        }

        var updatedPath = ROOT.resolve("updated");
        if (Files.notExists(updatedPath)) {
            Files.createDirectories(updatedPath);
        }

        try (var paths = Files.walk(ROOT, 1)) {
            paths.filter(Files::isRegularFile).forEach(file -> {
                try {
                    var knightOld = objectMapper.readValue(file.toFile(), KnightOld.class);
                    var attributes = new ArrayList<Attribute>();
                    knightOld.attributes.forEach(it -> attributes.add(new Attribute(it.type, it.name, it.file, it.rarity)));

                    var knight = new Knight(knightOld.id, knightOld.image, attributes);
                    objectMapper.writeValue(updatedPath.resolve(file.getFileName()).toFile(), knight);
                    log.info("Wrote updated metadata for: " + file);
                } catch (IOException cause) {
                    log.log(Level.SEVERE, "Unable to write metadata for file: " + file, cause);
                }
            });
        }
    }

}
