package fr.alasdiablo.mods.lib.api.data.blockstate;

import com.google.gson.JsonObject;
import fr.alasdiablo.mods.lib.api.util.ResourceLocations;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.IGeneratedBlockState;

import javax.annotation.ParametersAreNonnullByDefault;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class DioBlockStateProvider implements DataProvider {
    protected final Map<String, IGeneratedBlockState> registeredBlocks = new LinkedHashMap<>();

    private final PackOutput output;
    private final String     modId;

    public DioBlockStateProvider(PackOutput output, String modId) {
        this.output = output;
        this.modId  = modId;
    }

    /**
     * Function use to register all block states
     */
    protected abstract void registerStates();

    /**
     * Register a block state
     *
     * @param blockName  Name of the block
     * @param blockstate Block-state associated to the block
     */
    protected void addBlockState(String blockName, IGeneratedBlockState blockstate) {
        this.registeredBlocks.put(blockName, blockstate);
    }

    /**
     * Block-state builder uses to create a door block-state
     *
     * @param blockName Name of the door block
     */
    protected void door(String blockName) {
        ResourceLocation     rl_top          = ResourceLocations.of(this.modId, "block/" + blockName + "_top");
        ResourceLocation     rl_top_hinge    = ResourceLocations.of(this.modId, "block/" + blockName + "_top_hinge");
        ResourceLocation     rl_bottom       = ResourceLocations.of(this.modId, "block/" + blockName + "_bottom");
        ResourceLocation     rl_bottom_hinge = ResourceLocations.of(this.modId, "block/" + blockName + "_bottom_hinge");
        DioBlockStateBuilder stateBuilder    = new DioBlockStateBuilder();

        stateBuilder.addVariants("facing=east,half=lower,hinge=left,open=false", rl_bottom);
        stateBuilder.addVariants("facing=east,half=lower,hinge=left,open=true", 0, 90, rl_bottom_hinge);
        stateBuilder.addVariants("facing=east,half=lower,hinge=right,open=false", 0, 0, rl_bottom_hinge);
        stateBuilder.addVariants("facing=east,half=lower,hinge=right,open=true", 0, 270, rl_bottom);
        stateBuilder.addVariants("facing=east,half=upper,hinge=left,open=false", 0, 0, rl_top);
        stateBuilder.addVariants("facing=east,half=upper,hinge=left,open=true", 0, 90, rl_top_hinge);
        stateBuilder.addVariants("facing=east,half=upper,hinge=right,open=false", 0, 0, rl_top_hinge);
        stateBuilder.addVariants("facing=east,half=upper,hinge=right,open=true", 0, 270, rl_top);

        stateBuilder.addVariants("facing=north,half=lower,hinge=left,open=false", 0, 270, rl_bottom);
        stateBuilder.addVariants("facing=north,half=lower,hinge=left,open=true", 0, 0, rl_bottom_hinge);
        stateBuilder.addVariants("facing=north,half=lower,hinge=right,open=false", 0, 270, rl_bottom_hinge);
        stateBuilder.addVariants("facing=north,half=lower,hinge=right,open=true", 0, 180, rl_bottom);
        stateBuilder.addVariants("facing=north,half=upper,hinge=left,open=false", 0, 270, rl_top);
        stateBuilder.addVariants("facing=north,half=upper,hinge=left,open=true", 0, 0, rl_top_hinge);
        stateBuilder.addVariants("facing=north,half=upper,hinge=right,open=false", 0, 270, rl_top_hinge);
        stateBuilder.addVariants("facing=north,half=upper,hinge=right,open=true", 0, 180, rl_top);

        stateBuilder.addVariants("facing=south,half=lower,hinge=left,open=false", 0, 90, rl_bottom);
        stateBuilder.addVariants("facing=south,half=lower,hinge=left,open=true", 0, 180, rl_bottom_hinge);
        stateBuilder.addVariants("facing=south,half=lower,hinge=right,open=false", 0, 90, rl_bottom_hinge);
        stateBuilder.addVariants("facing=south,half=lower,hinge=right,open=true", 0, 0, rl_bottom);
        stateBuilder.addVariants("facing=south,half=upper,hinge=left,open=false", 0, 90, rl_top);
        stateBuilder.addVariants("facing=south,half=upper,hinge=left,open=true", 0, 180, rl_top_hinge);
        stateBuilder.addVariants("facing=south,half=upper,hinge=right,open=false", 0, 90, rl_top_hinge);
        stateBuilder.addVariants("facing=south,half=upper,hinge=right,open=true", 0, 0, rl_top);

        stateBuilder.addVariants("facing=west,half=lower,hinge=left,open=false", 0, 180, rl_bottom);
        stateBuilder.addVariants("facing=west,half=lower,hinge=left,open=true", 0, 270, rl_bottom_hinge);
        stateBuilder.addVariants("facing=west,half=lower,hinge=right,open=false", 0, 180, rl_bottom_hinge);
        stateBuilder.addVariants("facing=west,half=lower,hinge=right,open=true", 0, 90, rl_bottom);
        stateBuilder.addVariants("facing=west,half=upper,hinge=left,open=false", 0, 180, rl_top);
        stateBuilder.addVariants("facing=west,half=upper,hinge=left,open=true", 0, 270, rl_top_hinge);
        stateBuilder.addVariants("facing=west,half=upper,hinge=right,open=false", 0, 180, rl_top_hinge);
        stateBuilder.addVariants("facing=west,half=upper,hinge=right,open=true", 0, 90, rl_top);

        this.addBlockState(blockName, stateBuilder);
    }

    /**
     * Block-state builder uses to create a button block-state
     *
     * @param blockName Name of the button block
     */
    protected void button(String blockName) {
        ResourceLocation     rl           = ResourceLocations.of(this.modId, "block/" + blockName);
        ResourceLocation     rl_pressed   = ResourceLocations.of(this.modId, "block/" + blockName + "_pressed");
        DioBlockStateBuilder stateBuilder = new DioBlockStateBuilder();

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

    /**
     * Block-state builder uses to create a trapdoor block-state
     *
     * @param blockName Name of the trapdoor block
     */
    protected void trapdoor(String blockName) {
        ResourceLocation     rl_bottom    = ResourceLocations.of(this.modId, "block/" + blockName + "_bottom");
        ResourceLocation     rl_top       = ResourceLocations.of(this.modId, "block/" + blockName + "_top");
        ResourceLocation     rl_open      = ResourceLocations.of(this.modId, "block/" + blockName + "_open");
        DioBlockStateBuilder stateBuilder = new DioBlockStateBuilder();

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
        stateBuilder.addVariants("facing=west,half=top,open=true", 0, 270, rl_open);

        stateBuilder.addVariants("facing=south,half=bottom,open=false", rl_bottom);
        stateBuilder.addVariants("facing=south,half=bottom,open=true", 0, 180, rl_open);
        stateBuilder.addVariants("facing=south,half=top,open=false", rl_top);
        stateBuilder.addVariants("facing=south,half=top,open=true", 0, 180, rl_open);

        this.addBlockState(blockName, stateBuilder);
    }

    /**
     * Block-state builder uses to create a stairs block-state
     *
     * @param blockName Name of the stairs block
     */
    protected void stairs(String blockName) {
        ResourceLocation     rl           = ResourceLocations.of(this.modId, "block/" + blockName);
        ResourceLocation     rl_inner     = ResourceLocations.of(this.modId, "block/" + blockName + "_inner");
        ResourceLocation     rl_outer     = ResourceLocations.of(this.modId, "block/" + blockName + "_outer");
        DioBlockStateBuilder stateBuilder = new DioBlockStateBuilder();

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

    /**
     * Block-state builder uses to create a door block-state
     *
     * @param slabBlockName Name of the slab block
     * @param fullBlockName Name of the full block
     */
    protected void slab(String slabBlockName, String fullBlockName) {
        ResourceLocation     rl            = ResourceLocations.of(this.modId, "block/" + slabBlockName);
        ResourceLocation     rl_top        = ResourceLocations.of(this.modId, "block/" + slabBlockName + "_top");
        ResourceLocation     rl_full_block = ResourceLocations.of(this.modId, "block/" + fullBlockName);
        DioBlockStateBuilder stateBuilder  = new DioBlockStateBuilder();
        stateBuilder.addVariants("type=bottom", rl);
        stateBuilder.addVariants("type=top", rl_top);
        stateBuilder.addVariants("type=double", rl_full_block);
        this.addBlockState(slabBlockName, stateBuilder);
    }

    /**
     * Block-state builder uses to create a pressure plate block-state
     *
     * @param blockName Name of the pressure plate block
     */
    protected void pressurePlate(String blockName) {
        ResourceLocation     rl           = ResourceLocations.of(this.modId, "block/" + blockName);
        ResourceLocation     rl_down      = ResourceLocations.of(this.modId, "block/" + blockName + "_down");
        DioBlockStateBuilder stateBuilder = new DioBlockStateBuilder();
        stateBuilder.addVariants("powered=false", rl);
        stateBuilder.addVariants("powered=true", rl_down);
        this.addBlockState(blockName, stateBuilder);
    }

    /**
     * Block-state builder uses to create a wooden fence gate block-state
     *
     * @param blockName Name of the wooden face gate block
     */
    protected void woodenFenceGate(String blockName) {
        ResourceLocation     rl           = ResourceLocations.of(this.modId, "block/" + blockName);
        ResourceLocation     rl_open      = ResourceLocations.of(this.modId, "block/" + blockName + "_open");
        ResourceLocation     rl_wall      = ResourceLocations.of(this.modId, "block/" + blockName + "_wall");
        ResourceLocation     rl_wall_open = ResourceLocations.of(this.modId, "block/" + blockName + "_wall_open");
        DioBlockStateBuilder stateBuilder = new DioBlockStateBuilder();
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

    /**
     * Block-state builder uses to create a wooden fence block-state
     *
     * @param blockName Name of the wooden fence block
     */
    protected void woodenFence(String blockName) {
        ResourceLocation              rl_post      = ResourceLocations.of(this.modId, "block/" + blockName + "_post");
        ResourceLocation              rl_side      = ResourceLocations.of(this.modId, "block/" + blockName + "_side");
        DioMultipartBlockStateBuilder stateBuilder = new DioMultipartBlockStateBuilder();
        stateBuilder.addMultipart(rl_post);
        stateBuilder.addMultipart(rl_side, true, 0, 0, true, false, false, false);
        stateBuilder.addMultipart(rl_side, true, 0, 90, false, true, false, false);
        stateBuilder.addMultipart(rl_side, true, 0, 180, false, false, true, false);
        stateBuilder.addMultipart(rl_side, true, 0, 270, false, false, false, true);
        this.addBlockState(blockName, stateBuilder);
    }

    /**
     * Block-state builder uses to create a pillar block-state
     *
     * @param blockName Name of the pillar block
     */
    protected void pillar(String blockName) {
        ResourceLocation     rl            = ResourceLocations.of(this.modId, "block/" + blockName);
        ResourceLocation     rl_horizontal = ResourceLocations.of(this.modId, "block/" + blockName + "_horizontal");
        DioBlockStateBuilder stateBuilder  = new DioBlockStateBuilder();
        stateBuilder.addVariants("axis=x", 90, 90, rl_horizontal);
        stateBuilder.addVariants("axis=y", 0, 0, rl);
        stateBuilder.addVariants("axis=z", 90, 0, rl_horizontal);
        this.addBlockState(blockName, stateBuilder);
    }

    /**
     * Block-state builder uses to create a sapling block-state
     *
     * @param blockName Name of the sapling block
     */
    protected void sapling(String blockName) {
        this.cubeAll(blockName);
    }

    /**
     * Block-state builder uses to create a default block-state
     *
     * @param blockName Name of the block
     */
    protected void cubeAll(String blockName) {
        ResourceLocation     rl           = ResourceLocations.of(this.modId, "block/" + blockName);
        DioBlockStateBuilder stateBuilder = new DioBlockStateBuilder();
        stateBuilder.addVariants("", rl);
        this.addBlockState(blockName, stateBuilder);
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        this.registeredBlocks.clear();
        this.registerStates();
        return CompletableFuture.allOf(
                registeredBlocks.entrySet().stream().map(
                        entry -> this.saveBlockState(cache, entry.getValue().toJson(), entry.getKey())
                ).toArray(CompletableFuture[]::new)
        );
    }

    private CompletableFuture<?> saveBlockState(CachedOutput cache, JsonObject stateJson, String blockName) {
        Path   mainOutput = this.output.getOutputFolder();
        String pathSuffix = "assets/" + this.modId + "/blockstates/" + blockName + ".json";
        Path   outputPath = mainOutput.resolve(pathSuffix);
        return DataProvider.saveStable(cache, stateJson, outputPath);
    }

    @Override
    public String getName() {
        return "Block States: " + this.modId;
    }
}