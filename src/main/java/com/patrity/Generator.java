package com.patrity;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Generator {
    private final Random random = new Random();
    private final ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final Path BASE_OUTPUT_PATH = Paths.get("output");

    public void createNFT(int index) {
        final String baseUrl = "https://nft.nobilitytoken.com/knights/image/";
        final int totalLayers = 9;

        List<Attribute> attributes = new ArrayList<>();

        for (int i = 0; i <= totalLayers - 1; i++) {
            AttributeType currentType = AttributeType.values()[i];
            if (i == 0)
                attributes.add(getAttribute(currentType, null));
            else
                attributes.add(getAttribute(currentType, attributes.get(i-1)));
        }
        Knight knight = new Knight(index, baseUrl + index + ".png", attributes);
        generateImage(knight);
        generateMetaData(knight);
    }

    private Attribute getAttribute(AttributeType type, Attribute previous) {
        Rarity rarity = Rarity.getRandomRarity();
        File file = getRandomFile(type.folderPath, rarity, type, previous);
        if (file == null) {
            return new Attribute(type, null, null, rarity);
        }
        String fileName = file.getName().substring(2).replace(".png", "");
        return new Attribute(type, fileName, file, rarity);
    }


    private File getRandomFile(String path, Rarity rarity, AttributeType type, Attribute previous) {
        File folder = new File(path);
        List<File> fileList = Arrays.stream(Objects.requireNonNull(folder.listFiles())).toList();

        List<File> filtered = fileList.stream().filter((file) -> {
            String rarityChar = rarity.prefix;
            if (type == AttributeType.PAULDRON) {
                String fileName = file.getName().toLowerCase();
                String prevType = previous.name.substring(0, 4).toLowerCase();
                rarityChar = previous.rarity.prefix;
                return file.getName().startsWith(rarityChar) && (fileName.contains(prevType));
            } else {
                return file.getName().startsWith(rarityChar);
            }
        }).toList();

        if (filtered.size() == 0) {
            return null;
        }

        return filtered.get(random.nextInt(filtered.size()));
    }

    @SneakyThrows
    private void generateImage(Knight knight) {
        service.submit(() -> {
            List<BufferedImage> imgList = getAttributeImages(knight);
            BufferedImage combined = new BufferedImage(2000, 2000, BufferedImage.TYPE_INT_ARGB);
            Graphics gfx = combined.getGraphics();

            imgList.forEach((img) -> {
                gfx.drawImage(img, 0, 0, null);
            });

            try {
                Path path = BASE_OUTPUT_PATH.resolve("images");
                if (!Files.exists(path)) Files.createDirectories(path);
                ImageIO.write(combined, "PNG", path.resolve(knight.id + ".png").toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @SneakyThrows
    private List<BufferedImage> getAttributeImages(Knight knight) {
        List<BufferedImage> imgList = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            if (knight.attributes.get(i).file != null)
                imgList.add(ImageIO.read(knight.attributes.get(i).file));
        }
        return imgList;
    }

    @SneakyThrows
    private void generateMetaData(Knight knight) {
        Path path = BASE_OUTPUT_PATH.resolve("metadata");
        if (!Files.exists(path)) Files.createDirectories(path);
        Utils.mapper.writeValue(path.resolve(knight.id + ".json").toFile(), knight);
    }
}
