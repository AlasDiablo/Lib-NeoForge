package fr.alasdiablo.mods.lib.api.data.blockstate;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.IGeneratedBlockState;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Class used to create simple blockstate
 */
@SuppressWarnings("unused")
public class DioBlockStateBuilder implements IGeneratedBlockState {

    /**
     * Map used to store the block state
     */
    private final Map<String, JsonObject> variants;

    /**
     * Default constructor
     */
    public DioBlockStateBuilder() {
        this.variants = new HashMap<>();
    }

    /**
     * Create a simple blockstate with rotation and uvlock
     *
     * @param stateNameIn Name of the state
     * @param rotateXIn   Rotation on x of the state
     * @param rotateYIn   Rotation on y of the state
     * @param uvlock      UVLock of the state
     * @param modelPathIn Model use by the state
     */
    public void addVariants(@NotNull String stateNameIn, int rotateXIn, int rotateYIn, boolean uvlock, @NotNull ResourceLocation modelPathIn) {
        var variant = new JsonObject();
        // Set model
        variant.addProperty("model", modelPathIn.toString());
        // Add rotation on x
        if (rotateXIn != 0) {
            variant.addProperty("x", rotateXIn);
        }
        // Add rotation on y
        if (rotateYIn != 0) {
            variant.addProperty("y", rotateYIn);
        }
        // Add uvlock
        if (uvlock) {
            variant.addProperty("uvlock", true);
        }
        // Store the state
        this.variants.put(stateNameIn, variant);
    }

    /**
     * Create a simple blockstate with rotation
     *
     * @param stateNameIn Name of the state
     * @param rotateXIn   Rotation on x of the state
     * @param rotateYIn   Rotation on y of the state
     * @param modelPathIn Model use by the state
     */
    public void addVariants(@NotNull String stateNameIn, int rotateXIn, int rotateYIn, @NotNull ResourceLocation modelPathIn) {
        this.addVariants(stateNameIn, rotateXIn, rotateYIn, false, modelPathIn);
    }

    /**
     * Create a simple blockstate
     *
     * @param stateNameIn Name of the state
     * @param modelPathIn Model use by the state
     */
    public void addVariants(String stateNameIn, @NotNull ResourceLocation modelPathIn) {
        this.addVariants(stateNameIn, 0, 0, false, modelPathIn);
    }

    /**
     * Add a blockstate variant
     *
     * @param variant blockstate variant
     */
    public void addVariants(@NotNull DioBlockStateVariant variant) {
        this.variants.put(variant.key(), variant.json());
    }

    /**
     * Export the blockstate into json
     *
     * @return Json representation of the blockstate
     */
    @Override
    public @NotNull JsonObject toJson() {
        // Create blockstate variants object and add all variants
        var blockStateVariants = new JsonObject();
        this.variants.forEach(blockStateVariants::add);
        // Create a blockstate object and add variants in it
        var blockState = new JsonObject();
        blockState.add("variants", blockStateVariants);
        // Return the blockstate
        return blockState;
    }
}
