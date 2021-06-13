package fr.alasdiablo.diolib.data;

import com.google.gson.JsonObject;
import fr.alasdiablo.diolib.lang.ImmutablePair;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.IGeneratedBlockstate;

import java.util.ArrayList;
import java.util.List;

public class BlockStateBuilder implements IGeneratedBlockstate {

    private final List<ImmutablePair<String, JsonObject>> variants;

    public BlockStateBuilder() {
        this.variants = new ArrayList<>();
    }

    public void addVariants(String stateNameIn, int rotateXIn, int rotateYIn, ResourceLocation modelPathIn) {
        JsonObject obj = new JsonObject();
        obj.addProperty("model", modelPathIn.toString());
        obj.addProperty("x", rotateXIn);
        obj.addProperty("y", rotateYIn);
        this.variants.add(new ImmutablePair<>(stateNameIn, obj));
    }

    public void addVariants(String stateNameIn, ResourceLocation modelPathIn) {
        JsonObject obj = new JsonObject();
        obj.addProperty("model", modelPathIn.toString());
        this.variants.add(new ImmutablePair<>(stateNameIn, obj));
    }

    @Override
    public JsonObject toJson() {
        final JsonObject variantsObj = new JsonObject();
        this.variants.forEach(variant -> {
            variantsObj.add(variant.getKey(), variant.getValue());
        });
        final JsonObject blockState = new JsonObject();
        blockState.add("variants", variantsObj);
        return blockState;
    }
}
