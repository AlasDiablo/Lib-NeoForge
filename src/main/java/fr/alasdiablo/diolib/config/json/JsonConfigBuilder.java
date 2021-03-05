package fr.alasdiablo.diolib.config.json;

import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonConfigBuilder {

    private static List<String> modIDList = new ArrayList<>();

    private final List<String> element;

    private JsonConfigBuilder(String modID, Path configDir, JsonConfig... configElements) throws DuplicatedJsonConfigException, IOException {
        this.element = new ArrayList<>();

        Arrays.stream(configElements).forEach(e -> {
                if (element.contains(e.getName())) {
                    throw new DuplicatedJsonConfigException(e.getName(), modID);
                } else {
                    e.filePath(Paths.get(configDir.toString(), e.getName() + ".json"));
                    element.add(e.getName());
                }
        });

        for (JsonConfig e: configElements) {
            e.preRead();
        }
    }

    public static void createJsonConfig(String modID, JsonConfig... configElements) throws DuplicatedJsonConfigException, IOException {
        if (modIDList.contains(modID)) {
            throw new DuplicatedJsonConfigException(modID);
        }


        Path path = Paths.get(FMLPaths.CONFIGDIR.get().toAbsolutePath().toString(), modID);
        try {
            Files.createDirectory(path);
            modIDList.add(modID);
        } catch (FileAlreadyExistsException ignored) {}

        new JsonConfigBuilder(modID, path, configElements);
    }
}
