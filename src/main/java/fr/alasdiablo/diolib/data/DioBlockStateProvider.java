package fr.alasdiablo.diolib.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.alasdiablo.diolib.DiaboloLib;
import fr.alasdiablo.diolib.util.Utils;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.IGeneratedBlockstate;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("unused")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class DioBlockStateProvider implements DataProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    protected final Map<String, IGeneratedBlockstate> registeredBlocks = new LinkedHashMap<>();

    private final DataGenerator generator;
    private final String modid;

    public DioBlockStateProvider(DataGenerator generator, String modid) {
        this.generator = generator;
        this.modid = modid;
    }

    protected abstract void registerStates();

    protected void addBlockState(String blockName, IGeneratedBlockstate blockstate) {
        this.registeredBlocks.put(blockName, blockstate);
    }

    protected void woodenFenceGate(String blockName) {
        ResourceLocation rl = Utils.rl(this.modid, "block/" + blockName);
        ResourceLocation rl_open = Utils.rl(this.modid, "block/" + blockName + "_open");
        ResourceLocation rl_wall = Utils.rl(this.modid, "block/" + blockName + "_wall");
        ResourceLocation rl_wall_open = Utils.rl(this.modid, "block/" + blockName + "_wall_open");

    }

    protected void woodenFence(String blockName) {
        ResourceLocation rl_post = Utils.rl(this.modid, "block/" + blockName + "_post");
        ResourceLocation rl_side = Utils.rl(this.modid, "block/" + blockName + "_side");
        MultipartBlockStateBuilder stateBuilder = new MultipartBlockStateBuilder();
        stateBuilder.addMultipart(rl_post);
        stateBuilder.addMultipart(rl_post, true, 0, 0  , true , false, false, false);
        stateBuilder.addMultipart(rl_post, true, 0, 90 , false, true , false, false);
        stateBuilder.addMultipart(rl_post, true, 0, 180, false, false, true , false);
        stateBuilder.addMultipart(rl_post, true, 0, 270, false, false, false, true );
        this.addBlockState(blockName, stateBuilder);
    }

    protected void pillar(String blockName) {
        ResourceLocation rl = Utils.rl(this.modid, "block/" + blockName);
        BlockStateBuilder stateBuilder = new BlockStateBuilder();
        stateBuilder.addVariants("axis=x", 90, 90, rl);
        stateBuilder.addVariants("axis=y", 0, 0, rl);
        stateBuilder.addVariants("axis=z", 90, 0, rl);
        this.addBlockState(blockName, stateBuilder);
    }

    protected void sapling(String blockName) {
        this.cubeAll(blockName);
    }

    protected void cubeAll(String blockName) {
        ResourceLocation rl = Utils.rl(this.modid, "block/" + blockName);
        BlockStateBuilder stateBuilder = new BlockStateBuilder();
        stateBuilder.addVariants("", rl);
        this.addBlockState(blockName, stateBuilder);
    }

    @Override
    public void run(HashCache cache) {
        this.registeredBlocks.clear();
        this.registerStates();
        for (Map.Entry<String, IGeneratedBlockstate> entry : registeredBlocks.entrySet()) {
            this.saveBlockState(cache, entry.getValue().toJson(), entry.getKey());
        }
    }

    private void saveBlockState(HashCache cache, JsonObject stateJson, String blockName) {
        Path mainOutput = generator.getOutputFolder();
        String pathSuffix = "assets/" + modid + "/blockstates/" + blockName + ".json";
        Path outputPath = mainOutput.resolve(pathSuffix);
        try {
            DataProvider.save(GSON, cache, stateJson, outputPath);
        } catch (IOException e) {
            DiaboloLib.logger.error("Couldn't save blockstate to {}", outputPath, e);
        }
    }

    @Override
    public String getName() {
        return "Block States: " + this.modid;
    }
}