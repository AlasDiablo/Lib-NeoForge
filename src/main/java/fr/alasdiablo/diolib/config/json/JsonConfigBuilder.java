package fr.alasdiablo.diolib.config.json;

import fr.alasdiablo.diolib.DiaboloLib;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@SuppressWarnings("unused")
public class JsonConfigBuilder {

    private static final List<String> modIDList = new ArrayList<>();

    private final Map<String, JsonConfig> jsonConfigs;
    private final String modID;
    private final List<String> configDir;

    public JsonConfigBuilder(String modID) throws DuplicatedJsonConfigException {
        this.modID = modID;

        if (modIDList.contains(this.modID)) {
            throw new DuplicatedJsonConfigException(this.modID);
        }

        this.jsonConfigs = new HashMap<>();
        this.configDir = Collections.singletonList(modID);
    }

    public JsonConfigBuilder(String modID, String... configSubDir) throws DuplicatedJsonConfigException {
        this.modID = modID + "/" + String.join("/", configSubDir);

        if (modIDList.contains(this.modID)) {
            throw new DuplicatedJsonConfigException(this.modID);
        }

        this.jsonConfigs = new HashMap<>();
        List<String> path = new ArrayList<>(Collections.singletonList(modID));
        path.addAll(Arrays.asList(configSubDir));
        this.configDir = path;
    }


    public JsonConfigBuilder add(JsonConfig jsonConfig) throws DuplicatedJsonConfigException {
        if (!this.jsonConfigs.containsKey(jsonConfig.getName()))
            this.jsonConfigs.put(jsonConfig.getName(), jsonConfig);
        else
            throw new DuplicatedJsonConfigException(jsonConfig.getName(), modID);
        return this;
    }


    public void build() throws IOException {
        String currentPath = FMLPaths.CONFIGDIR.get().toAbsolutePath().toString();
        Path path = null;

        for (String e: this.configDir) {
            Path tmpPath = Paths.get(currentPath, e);
            currentPath = tmpPath.toString();
            try {
                path = Files.createDirectory(tmpPath);
            } catch (FileAlreadyExistsException exception) {
                path = tmpPath;
            }
            DiaboloLib.logger.debug("Create folder: " + path);
        }
        modIDList.add(modID);

        final Path finalPath = path;
        this.jsonConfigs.values().forEach(e -> e.filePath(Paths.get(finalPath.toString(), e.getName() + ".json")));

        for (JsonConfig e: this.jsonConfigs.values()) {
            e.initOrLoad();
        }
    }
}
