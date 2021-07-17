package fr.alasdiablo.diolib.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.alasdiablo.diolib.DiaboloLib;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraftforge.client.model.generators.IGeneratedBlockstate;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("unused")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class BlockStateProvider implements IDataProvider {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

    protected final Map<String, IGeneratedBlockstate> registeredBlocks = new LinkedHashMap<>();

    private final DataGenerator generator;
    private final String modid;

    public BlockStateProvider(DataGenerator generator, String modid) {
        this.generator = generator;
        this.modid = modid;
    }

    protected abstract void registerStates();

    protected void addBlockState(String blockName, IGeneratedBlockstate blockstate) {
        this.registeredBlocks.put(blockName, blockstate);
    }

    @Override
    public void run(DirectoryCache cache) {
        this.registeredBlocks.clear();
        this.registerStates();
        for (Map.Entry<String, IGeneratedBlockstate> entry : registeredBlocks.entrySet()) {
            this.saveBlockState(cache, entry.getValue().toJson(), entry.getKey());
        }
    }

    private void saveBlockState(DirectoryCache cache, JsonObject stateJson, String blockName) {
        Path mainOutput = generator.getOutputFolder();
        String pathSuffix = "assets/" + modid + "/blockstates/" + blockName + ".json";
        Path outputPath = mainOutput.resolve(pathSuffix);
        try {
            IDataProvider.save(GSON, cache, stateJson, outputPath);
        } catch (IOException e) {
            DiaboloLib.logger.error("Couldn't save blockstate to {}", outputPath, e);
        }
    }

    @Override
    public String getName() {
        return "Block States: " + this.modid;
    }
}