package fr.alasdiablo.diolib.data;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.IGeneratedBlockstate;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class BlockStateBuilder implements IGeneratedBlockstate {

    private final Map<String, JsonObject> variants;

    public BlockStateBuilder() {
        this.variants = new HashMap<>();
    }

    public void addVariants(String stateNameIn, int rotateXIn, int rotateYIn, boolean uvlock, ResourceLocation modelPathIn) {
        JsonObject obj = new JsonObject();
        obj.addProperty("model", modelPathIn.toString());
        if (rotateXIn != 0) obj.addProperty("x", rotateXIn);
        if (rotateYIn != 0) obj.addProperty("y", rotateYIn);
        if (uvlock) obj.addProperty("uvlock", true);
        this.variants.put(stateNameIn, obj);
    }

    public void addVariants(String stateNameIn, int rotateXIn, int rotateYIn, ResourceLocation modelPathIn) {
        JsonObject obj = new JsonObject();
        obj.addProperty("model", modelPathIn.toString());
        if (rotateXIn != 0) obj.addProperty("x", rotateXIn);
        if (rotateYIn != 0) obj.addProperty("y", rotateYIn);
        this.variants.put(stateNameIn, obj);
    }

    public void addVariants(String stateNameIn, ResourceLocation modelPathIn) {
        JsonObject obj = new JsonObject();
        obj.addProperty("model", modelPathIn.toString());
        this.variants.put(stateNameIn, obj);
    }

    public void addVariants(BlockStateVariant variant) {
        this.variants.put(variant.getKey(), variant.getJson());
    }

    @Override
    public JsonObject toJson() {
        final JsonObject variantsObj = new JsonObject();
        this.variants.forEach(variantsObj::add);
        final JsonObject blockState = new JsonObject();
        blockState.add("variants", variantsObj);
        return blockState;
    }
}
