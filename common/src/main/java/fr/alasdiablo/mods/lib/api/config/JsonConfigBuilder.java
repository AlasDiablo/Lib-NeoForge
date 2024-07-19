package fr.alasdiablo.mods.lib.api.config;

import fr.alasdiablo.mods.lib.Constants;
import fr.alasdiablo.mods.lib.platform.Services;
import org.jetbrains.annotations.NotNull;

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
    private final String                  modID;
    private final List<String>            configDir;

    public JsonConfigBuilder(String modID) throws DuplicatedJsonConfigException {
        this.modID = modID;

        if (modIDList.contains(this.modID)) {
            throw new DuplicatedJsonConfigException(this.modID);
        }

        this.jsonConfigs = new HashMap<>();
        this.configDir   = Collections.singletonList(modID);
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


    public JsonConfigBuilder add(@NotNull JsonConfig jsonConfig) throws DuplicatedJsonConfigException {
        if (!this.jsonConfigs.containsKey(jsonConfig.getName()))
            this.jsonConfigs.put(jsonConfig.getName(), jsonConfig);
        else
            throw new DuplicatedJsonConfigException(jsonConfig.getName(), modID);
        return this;
    }


    public void build() throws IOException {
        String currentPath = Services.PLATFORM.getConfigPath();
        Path   path        = null;

        for (String e: this.configDir) {
            Path tmpPath = Paths.get(currentPath, e);
            currentPath = tmpPath.toString();
            try {
                path = Files.createDirectory(tmpPath);
            } catch (FileAlreadyExistsException exception) {
                path = tmpPath;
            }
            Constants.LOG.debug("Create folder: {}", path);
        }
        modIDList.add(modID);

        final Path finalPath = path;
        this.jsonConfigs.values().forEach(e -> {
            assert finalPath != null;
            e.filePath(Paths.get(finalPath.toString(), e.getName() + ".json"));
        });

        for (JsonConfig e: this.jsonConfigs.values()) {
            e.initOrLoad();
        }
    }
}
