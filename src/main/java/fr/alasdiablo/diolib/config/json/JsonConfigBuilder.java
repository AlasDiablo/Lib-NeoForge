package fr.alasdiablo.diolib.config.json;

import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonConfigBuilder {

    private static final List<String> modIDList = new ArrayList<>();

    private final Map<String, JsonConfig> jsonConfigs;
    private final String modID;
    private final Path configDir;

    public JsonConfigBuilder(String modID) throws DuplicatedJsonConfigException {
        this.modID = modID;

        if (modIDList.contains(this.modID)) {
            throw new DuplicatedJsonConfigException(this.modID);
        }

        this.jsonConfigs = new HashMap<>();
        this.configDir = Paths.get(FMLPaths.CONFIGDIR.get().toAbsolutePath().toString(), modID);
    }

    public JsonConfigBuilder(String modID, String configDir) throws DuplicatedJsonConfigException {
        this.modID = modID + "/" + configDir;

        if (modIDList.contains(this.modID)) {
            throw new DuplicatedJsonConfigException(this.modID);
        }

        this.jsonConfigs = new HashMap<>();
        this.configDir = Paths.get(FMLPaths.CONFIGDIR.get().toAbsolutePath().toString(), modID, configDir);
    }


    public JsonConfigBuilder add(JsonConfig jsonConfig) throws DuplicatedJsonConfigException {
        if (!this.jsonConfigs.containsKey(jsonConfig.getName()))
            this.jsonConfigs.put(jsonConfig.getName(), jsonConfig);
        else
            throw new DuplicatedJsonConfigException(jsonConfig.getName(), modID);
        return this;
    }


    public void build() throws IOException {
        try {
            Files.createDirectory(this.configDir);
            modIDList.add(modID);
        } catch (FileAlreadyExistsException ignored) {}

        this.jsonConfigs.values().forEach(e -> e.filePath(Paths.get(configDir.toString(), e.getName() + ".json")));

        for (JsonConfig e: this.jsonConfigs.values()) {
            e.preRead();
        }
    }
}
