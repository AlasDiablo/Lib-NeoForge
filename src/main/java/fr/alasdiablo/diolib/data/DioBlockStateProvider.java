package fr.alasdiablo.diolib.data;

import com.google.common.collect.Lists;
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
    private final String mod_id;

    public DioBlockStateProvider(DataGenerator generator, String mod_id) {
        this.generator = generator;
        this.mod_id = mod_id;
    }

    protected abstract void registerStates();

    protected void addBlockState(String blockName, IGeneratedBlockstate blockstate) {
        this.registeredBlocks.put(blockName, blockstate);
    }

    protected void door(String blockName) {
        ResourceLocation rl_top = Utils.rl(this.mod_id, "block/" + blockName + "_top");
        ResourceLocation rl_top_hinge = Utils.rl(this.mod_id, "block/" + blockName + "_top_hinge");
        ResourceLocation rl_bottom = Utils.rl(this.mod_id, "block/" + blockName + "_bottom");
        ResourceLocation rl_bottom_hinge = Utils.rl(this.mod_id, "block/" + blockName + "_bottom_hinge");
        BlockStateBuilder stateBuilder = new BlockStateBuilder();

        stateBuilder.addVariants("facing=east,half=lower,hinge=left,open=false", rl_bottom);
        stateBuilder.addVariants("facing=east,half=lower,hinge=left,open=true", 0, 90, rl_bottom_hinge);
        stateBuilder.addVariants("facing=east,half=lower,hinge=right,open=false", 0, 0, rl_bottom_hinge);
        stateBuilder.addVariants("facing=east,half=lower,hinge=left,open=true", 0, 270, rl_bottom);
        stateBuilder.addVariants("facing=east,half=upper,hinge=left,open=false", 0, 0, rl_top);
        stateBuilder.addVariants("facing=east,half=upper,hinge=left,open=true", 0, 90, rl_top_hinge);
        stateBuilder.addVariants("facing=east,half=upper,hinge=right,open=false", 0, 0, rl_top_hinge);
        stateBuilder.addVariants("facing=east,half=upper,hinge=right,open=true", 0, 270, rl_top);

        stateBuilder.addVariants("facing=north,half=lower,hinge=left,open=false", 0, 270, rl_bottom);
        stateBuilder.addVariants("facing=north,half=lower,hinge=left,open=true", 0, 0, rl_bottom_hinge);
        stateBuilder.addVariants("facing=north,half=lower,hinge=right,open=false", 0, 270, rl_bottom_hinge);
        stateBuilder.addVariants("facing=north,half=lower,hinge=left,open=true", 0, 180, rl_bottom);
        stateBuilder.addVariants("facing=north,half=upper,hinge=left,open=false", 0, 270, rl_top);
        stateBuilder.addVariants("facing=north,half=upper,hinge=left,open=true", 0, 0, rl_top_hinge);
        stateBuilder.addVariants("facing=north,half=upper,hinge=right,open=false", 0, 270, rl_top_hinge);
        stateBuilder.addVariants("facing=north,half=upper,hinge=right,open=true", 0, 180, rl_top);

        stateBuilder.addVariants("facing=south,half=lower,hinge=left,open=false", 0, 90, rl_bottom);
        stateBuilder.addVariants("facing=south,half=lower,hinge=left,open=true", 0, 180, rl_bottom_hinge);
        stateBuilder.addVariants("facing=south,half=lower,hinge=right,open=false", 0, 90, rl_bottom_hinge);
        stateBuilder.addVariants("facing=south,half=lower,hinge=left,open=true", 0, 0, rl_bottom);
        stateBuilder.addVariants("facing=south,half=upper,hinge=left,open=false", 0, 90, rl_top);
        stateBuilder.addVariants("facing=south,half=upper,hinge=left,open=true", 0, 180, rl_top_hinge);
        stateBuilder.addVariants("facing=south,half=upper,hinge=right,open=false", 0, 90, rl_top_hinge);
        stateBuilder.addVariants("facing=south,half=upper,hinge=right,open=true", 0, 0, rl_top);

        stateBuilder.addVariants("facing=west,half=lower,hinge=left,open=false", 0, 180, rl_bottom);
        stateBuilder.addVariants("facing=west,half=lower,hinge=left,open=true", 0, 270, rl_bottom_hinge);
        stateBuilder.addVariants("facing=west,half=lower,hinge=right,open=false", 0, 180, rl_bottom_hinge);
        stateBuilder.addVariants("facing=west,half=lower,hinge=left,open=true", 0, 90, rl_bottom);
        stateBuilder.addVariants("facing=west,half=upper,hinge=left,open=false", 0, 180, rl_top);
        stateBuilder.addVariants("facing=west,half=upper,hinge=left,open=true", 0, 270, rl_top_hinge);
        stateBuilder.addVariants("facing=west,half=upper,hinge=right,open=false", 0, 180, rl_top_hinge);
        stateBuilder.addVariants("facing=west,half=upper,hinge=right,open=true", 0, 90, rl_top);

        this.addBlockState(blockName, stateBuilder);
    }

    protected void button(String blockName) {
        ResourceLocation rl = Utils.rl(this.mod_id, "block/" + blockName);
        ResourceLocation rl_pressed = Utils.rl(this.mod_id, "block/" + blockName + "_pressed");
        BlockStateBuilder stateBuilder = new BlockStateBuilder();

        stateBuilder.addVariants("face=ceiling,facing=east,powered=false", 180, 270, rl);
        stateBuilder.addVariants("face=ceiling,facing=east,powered=true", 180, 270, rl_pressed);
        stateBuilder.addVariants("face=ceiling,facing=north,powered=false", 180, 180, rl);
        stateBuilder.addVariants("face=ceiling,facing=north,powered=true", 180, 180, rl_pressed);
        stateBuilder.addVariants("face=ceiling,facing=north,powered=false", 180, 180, rl);
        stateBuilder.addVariants("face=ceiling,facing=north,powered=true", 180, 180, rl_pressed);
        stateBuilder.addVariants("face=ceiling,facing=south,powered=false", 180, 0, rl);
        stateBuilder.addVariants("face=ceiling,facing=south,powered=true", 180, 0, rl_pressed);
        stateBuilder.addVariants("face=ceiling,facing=west,powered=false", 90, 180, rl);
        stateBuilder.addVariants("face=ceiling,facing=west,powered=true", 90, 180, rl_pressed);

        stateBuilder.addVariants("face=floor,facing=east,powered=false", 0, 90, rl);
        stateBuilder.addVariants("face=floor,facing=east,powered=true", 0, 90, rl_pressed);
        stateBuilder.addVariants("face=floor,facing=north,powered=false", 0, 0, rl);
        stateBuilder.addVariants("face=floor,facing=north,powered=true", 0, 0, rl_pressed);
        stateBuilder.addVariants("face=floor,facing=south,powered=false", 0, 180, rl);
        stateBuilder.addVariants("face=floor,facing=south,powered=true", 0, 180, rl_pressed);
        stateBuilder.addVariants("face=floor,facing=west,powered=false", 0, 270, rl);
        stateBuilder.addVariants("face=floor,facing=west,powered=true", 0, 270, rl_pressed);

        stateBuilder.addVariants("face=wall,facing=east,powered=false", 90, 90, true, rl);
        stateBuilder.addVariants("face=wall,facing=east,powered=true", 90, 90, true, rl_pressed);
        stateBuilder.addVariants("face=wall,facing=north,powered=false", 90, 0, true, rl);
        stateBuilder.addVariants("face=wall,facing=north,powered=true", 90, 0, true, rl_pressed);
        stateBuilder.addVariants("face=wall,facing=south,powered=false", 90, 180, true, rl);
        stateBuilder.addVariants("face=wall,facing=south,powered=true", 90, 180, true, rl_pressed);
        stateBuilder.addVariants("face=wall,facing=west,powered=false", 90, 270, true, rl);
        stateBuilder.addVariants("face=wall,facing=west,powered=true", 90, 270, true, rl_pressed);

        this.addBlockState(blockName, stateBuilder);
    }

    protected void trapdoor(String blockName) {
        ResourceLocation rl_bottom = Utils.rl(this.mod_id, "block/" + blockName + "_bottom");
        ResourceLocation rl_top = Utils.rl(this.mod_id, "block/" + blockName + "_top");
        ResourceLocation rl_open = Utils.rl(this.mod_id, "block/" + blockName + "_open");
        BlockStateBuilder stateBuilder = new BlockStateBuilder();

        stateBuilder.addVariants("facing=east,half=bottom,open=false", rl_bottom);
        stateBuilder.addVariants("facing=east,half=bottom,open=true", 0, 90, rl_open);
        stateBuilder.addVariants("facing=east,half=top,open=false", rl_top);
        stateBuilder.addVariants("facing=east,half=top,open=true", 0, 90, rl_open);

        stateBuilder.addVariants("facing=north,half=bottom,open=false", rl_bottom);
        stateBuilder.addVariants("facing=north,half=bottom,open=true", rl_open);
        stateBuilder.addVariants("facing=north,half=top,open=false", rl_top);
        stateBuilder.addVariants("facing=north,half=top,open=true", rl_open);

        stateBuilder.addVariants("facing=west,half=bottom,open=false", rl_bottom);
        stateBuilder.addVariants("facing=west,half=bottom,open=true", 0, 270, rl_open);
        stateBuilder.addVariants("facing=west,half=top,open=false", rl_top);
        stateBuilder.addVariants("facing=west,half=top,open=true",0, 270, rl_open);

        stateBuilder.addVariants("facing=south,half=bottom,open=false", rl_bottom);
        stateBuilder.addVariants("facing=south,half=bottom,open=true", 0, 180, rl_open);
        stateBuilder.addVariants("facing=south,half=top,open=false", rl_top);
        stateBuilder.addVariants("facing=south,half=top,open=true",0, 180, rl_open);

        this.addBlockState(blockName, stateBuilder);
    }

    protected void stairs(String blockName) {
        ResourceLocation rl = Utils.rl(this.mod_id, "block/" + blockName);
        ResourceLocation rl_inner = Utils.rl(this.mod_id, "block/" + blockName + "_inner");
        ResourceLocation rl_outer = Utils.rl(this.mod_id, "block/" + blockName + "_outer");
        BlockStateBuilder stateBuilder = new BlockStateBuilder();

        stateBuilder.addVariants("facing=east,half=bottom,shape=inner_left", 0, 270, true, rl_inner);
        stateBuilder.addVariants("facing=east,half=bottom,shape=inner_right", 0, 0, false, rl_inner);
        stateBuilder.addVariants("facing=east,half=bottom,shape=outer_left", 0, 270, true, rl_outer);
        stateBuilder.addVariants("facing=east,half=bottom,shape=outer_right", 0, 0, false, rl_outer);
        stateBuilder.addVariants("facing=east,half=bottom,shape=straight", 0, 0, false, rl);
        stateBuilder.addVariants("facing=east,half=top,shape=inner_left", 180, 0, true, rl_inner);
        stateBuilder.addVariants("facing=east,half=top,shape=inner_right", 180, 90, true, rl_inner);
        stateBuilder.addVariants("facing=east,half=top,shape=outer_left", 180, 0, true, rl_outer);
        stateBuilder.addVariants("facing=east,half=top,shape=outer_right", 180, 90, true, rl_outer);
        stateBuilder.addVariants("facing=east,half=top,shape=straight", 180, 0, true, rl);

        stateBuilder.addVariants("facing=north,half=bottom,shape=inner_left", 0, 180, true, rl_inner);
        stateBuilder.addVariants("facing=north,half=bottom,shape=inner_right", 0, 270, true, rl_inner);
        stateBuilder.addVariants("facing=north,half=bottom,shape=outer_left", 0, 180, true, rl_outer);
        stateBuilder.addVariants("facing=north,half=bottom,shape=outer_right", 0, 270, true, rl_outer);
        stateBuilder.addVariants("facing=north,half=bottom,shape=straight", 0, 270, true, rl);
        stateBuilder.addVariants("facing=north,half=top,shape=inner_left", 180, 270, true, rl_inner);
        stateBuilder.addVariants("facing=north,half=top,shape=inner_right", 180, 0, true, rl_inner);
        stateBuilder.addVariants("facing=north,half=top,shape=outer_left", 180, 270, true, rl_outer);
        stateBuilder.addVariants("facing=north,half=top,shape=outer_right", 180, 0, true, rl_outer);
        stateBuilder.addVariants("facing=north,half=top,shape=straight", 180, 270, true, rl);

        stateBuilder.addVariants("facing=west,half=bottom,shape=inner_left", 0, 90, true, rl_inner);
        stateBuilder.addVariants("facing=west,half=bottom,shape=inner_right", 0, 180, true, rl_inner);
        stateBuilder.addVariants("facing=west,half=bottom,shape=outer_left", 0, 90, true, rl_outer);
        stateBuilder.addVariants("facing=west,half=bottom,shape=outer_right", 0, 180, true, rl_outer);
        stateBuilder.addVariants("facing=west,half=bottom,shape=straight", 0, 180, true, rl);
        stateBuilder.addVariants("facing=west,half=top,shape=inner_left", 180, 180, true, rl_inner);
        stateBuilder.addVariants("facing=west,half=top,shape=inner_right", 180, 270, true, rl_inner);
        stateBuilder.addVariants("facing=west,half=top,shape=outer_left", 180, 180, true, rl_outer);
        stateBuilder.addVariants("facing=west,half=top,shape=outer_right", 180, 270, true, rl_outer);
        stateBuilder.addVariants("facing=west,half=top,shape=straight", 180, 180, true, rl);

        stateBuilder.addVariants("facing=south,half=bottom,shape=inner_left", 0, 0, false, rl_inner);
        stateBuilder.addVariants("facing=south,half=bottom,shape=inner_right", 0, 90, true, rl_inner);
        stateBuilder.addVariants("facing=south,half=bottom,shape=outer_left", 0, 0, false, rl_outer);
        stateBuilder.addVariants("facing=south,half=bottom,shape=outer_right", 0, 90, true, rl_outer);
        stateBuilder.addVariants("facing=south,half=bottom,shape=straight", 0, 90, true, rl);
        stateBuilder.addVariants("facing=south,half=top,shape=inner_left", 180, 90, true, rl_inner);
        stateBuilder.addVariants("facing=south,half=top,shape=inner_right", 180, 180, true, rl_inner);
        stateBuilder.addVariants("facing=south,half=top,shape=outer_left", 180, 90, true, rl_outer);
        stateBuilder.addVariants("facing=south,half=top,shape=outer_right", 180, 180, true, rl_outer);
        stateBuilder.addVariants("facing=south,half=top,shape=straight", 180, 90, true, rl);

        this.addBlockState(blockName, stateBuilder);
    }

    protected void slab(String slabBlockName, String fullBlockName) {
        ResourceLocation rl = Utils.rl(this.mod_id, "block/" + slabBlockName);
        ResourceLocation rl_top = Utils.rl(this.mod_id, "block/" + slabBlockName + "_top");
        ResourceLocation rl_full_block = Utils.rl(this.mod_id, "block/" + fullBlockName);
        BlockStateBuilder stateBuilder = new BlockStateBuilder();
        stateBuilder.addVariants("type=bottom", rl);
        stateBuilder.addVariants("type=top", rl_top);
        stateBuilder.addVariants("type=double", rl_full_block);
        this.addBlockState(slabBlockName, stateBuilder);
    }

    protected void pressurePlate(String blockName) {
        ResourceLocation rl = Utils.rl(this.mod_id, "block/" + blockName);
        ResourceLocation rl_down = Utils.rl(this.mod_id, "block/" + blockName + "_down");
        BlockStateBuilder stateBuilder = new BlockStateBuilder();
        stateBuilder.addVariants("powered=false", rl);
        stateBuilder.addVariants("powered=false", rl_down);
        this.addBlockState(blockName, stateBuilder);
    }

    protected void woodenFenceGate(String blockName) {
        ResourceLocation rl = Utils.rl(this.mod_id, "block/" + blockName);
        ResourceLocation rl_open = Utils.rl(this.mod_id, "block/" + blockName + "_open");
        ResourceLocation rl_wall = Utils.rl(this.mod_id, "block/" + blockName + "_wall");
        ResourceLocation rl_wall_open = Utils.rl(this.mod_id, "block/" + blockName + "_wall_open");
        BlockStateBuilder stateBuilder = new BlockStateBuilder();
        stateBuilder.addVariants("facing=east,in_wall=false,open=false", 0, 270, true, rl);
        stateBuilder.addVariants("facing=east,in_wall=false,open=true", 0, 270, true, rl_open);
        stateBuilder.addVariants("facing=east,in_wall=true,open=false", 0, 270, true, rl_wall);
        stateBuilder.addVariants("facing=east,in_wall=true,open=true", 0, 270, true, rl_wall_open);

        stateBuilder.addVariants("facing=north,in_wall=false,open=false", 0, 180, true, rl);
        stateBuilder.addVariants("facing=north,in_wall=false,open=true", 0, 180, true, rl_open);
        stateBuilder.addVariants("facing=north,in_wall=true,open=false", 0, 180, true, rl_wall);
        stateBuilder.addVariants("facing=north,in_wall=true,open=true", 0, 180, true, rl_wall_open);

        stateBuilder.addVariants("facing=west,in_wall=false,open=false", 0, 90, true, rl);
        stateBuilder.addVariants("facing=west,in_wall=false,open=true", 0, 90, true, rl_open);
        stateBuilder.addVariants("facing=west,in_wall=true,open=false", 0, 90, true, rl_wall);
        stateBuilder.addVariants("facing=west,in_wall=true,open=true", 0, 90, true, rl_wall_open);

        stateBuilder.addVariants("facing=south,in_wall=false,open=false", 0, 0, true, rl);
        stateBuilder.addVariants("facing=south,in_wall=false,open=true", 0, 0, true, rl_open);
        stateBuilder.addVariants("facing=south,in_wall=true,open=false", 0, 0, true, rl_wall);
        stateBuilder.addVariants("facing=south,in_wall=true,open=true", 0, 0, true, rl_wall_open);

        this.addBlockState(blockName, stateBuilder);
    }

    protected void woodenFence(String blockName) {
        ResourceLocation rl_post = Utils.rl(this.mod_id, "block/" + blockName + "_post");
        ResourceLocation rl_side = Utils.rl(this.mod_id, "block/" + blockName + "_side");
        MultipartBlockStateBuilder stateBuilder = new MultipartBlockStateBuilder();
        stateBuilder.addMultipart(rl_post);
        stateBuilder.addMultipart(rl_post, true, 0, 0  , true , false, false, false);
        stateBuilder.addMultipart(rl_post, true, 0, 90 , false, true , false, false);
        stateBuilder.addMultipart(rl_post, true, 0, 180, false, false, true , false);
        stateBuilder.addMultipart(rl_post, true, 0, 270, false, false, false, true );
        this.addBlockState(blockName, stateBuilder);
    }

    protected void pillar(String blockName) {
        ResourceLocation rl = Utils.rl(this.mod_id, "block/" + blockName);
        ResourceLocation rl_horizontal = Utils.rl(this.mod_id, "block/" + blockName + "_horizontal");
        BlockStateBuilder stateBuilder = new BlockStateBuilder();
        stateBuilder.addVariants("axis=x", 90, 90, rl_horizontal);
        stateBuilder.addVariants("axis=y", 0, 0, rl);
        stateBuilder.addVariants("axis=z", 90, 0, rl_horizontal);
        this.addBlockState(blockName, stateBuilder);
    }

    protected void sapling(String blockName) {
        this.cubeAll(blockName);
    }

    protected void cubeAll(String blockName) {
        ResourceLocation rl = Utils.rl(this.mod_id, "block/" + blockName);
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
        String pathSuffix = "assets/" + mod_id + "/blockstates/" + blockName + ".json";
        Path outputPath = mainOutput.resolve(pathSuffix);
        try {
            DataProvider.save(GSON, cache, stateJson, outputPath);
        } catch (IOException e) {
            DiaboloLib.logger.error("Couldn't save blockstate to {}", outputPath, e);
        }
    }

    @Override
    public String getName() {
        return "Block States: " + this.mod_id;
    }
}