package fr.alasdiablo.diolib.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.alasdiablo.diolib.lang.ImmutablePair;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.IGeneratedBlockstate;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class MultipartBlockStateBuilder implements IGeneratedBlockstate {

    private final List<ImmutablePair<JsonObject, JsonObject>> multiparts;

    public MultipartBlockStateBuilder() {
        this.multiparts = new ArrayList<>();
    }

    public void addMultipart(ResourceLocation modelPathIn) {
        JsonObject apply = new JsonObject();
        apply.addProperty("model", modelPathIn.toString());
        this.multiparts.add(new ImmutablePair<>(null, apply));
    }

    public void addMultipart(ResourceLocation modelPathIn, boolean uvLockIn) {
        JsonObject apply = new JsonObject();
        apply.addProperty("model", modelPathIn.toString());
        if (uvLockIn) apply.addProperty("uvlock", true);
        this.multiparts.add(new ImmutablePair<>(null, apply));
    }

    public void addMultipart(ResourceLocation modelPathIn, boolean uvLockIn, int xIn, int yIn) {
        JsonObject apply = new JsonObject();
        apply.addProperty("model", modelPathIn.toString());
        if (uvLockIn) apply.addProperty("uvlock", true);
        if (xIn != 0) apply.addProperty("x", xIn);
        if (yIn != 0) apply.addProperty("y", yIn);
        this.multiparts.add(new ImmutablePair<>(null, apply));
    }

    public void addMultipart(ResourceLocation modelPathIn, boolean north, boolean east, boolean south, boolean west) {
        JsonObject when = new JsonObject();
        JsonObject apply = new JsonObject();
        this.handleCardinalPoint(when, north, east, south, west);
        apply.addProperty("model", modelPathIn.toString());
        this.multiparts.add(new ImmutablePair<>(when, apply));
    }

    public void addMultipart(ResourceLocation modelPathIn, boolean uvLockIn, boolean north, boolean east, boolean south, boolean west) {
        JsonObject when = new JsonObject();
        JsonObject apply = new JsonObject();
        this.handleCardinalPoint(when, north, east, south, west);
        apply.addProperty("model", modelPathIn.toString());
        if (uvLockIn) apply.addProperty("uvlock", true);
        this.multiparts.add(new ImmutablePair<>(when, apply));
    }

    public void addMultipart(ResourceLocation modelPathIn, boolean uvLockIn, int xIn, int yIn, boolean north, boolean east, boolean south, boolean west) {
        JsonObject when = new JsonObject();
        JsonObject apply = new JsonObject();
        this.handleCardinalPoint(when, north, east, south, west);
        apply.addProperty("model", modelPathIn.toString());
        if (uvLockIn) apply.addProperty("uvlock", true);
        if (xIn != 0) apply.addProperty("x", xIn);
        if (yIn != 0) apply.addProperty("y", yIn);
        this.multiparts.add(new ImmutablePair<>(when, apply));
    }

    private void handleCardinalPoint(JsonObject when, boolean north, boolean east, boolean south, boolean west) {
        if (north) when.addProperty("north", true);
        if (east) when.addProperty("east", true);
        if (south) when.addProperty("south", true);
        if (west) when.addProperty("west", true);
    }

    @Override
    public JsonObject toJson() {
        final JsonArray multipartObj = new JsonArray();
        this.multiparts.forEach(multipart -> {
            JsonObject object = new JsonObject();
            if (multipart.getKey() != null) object.add("when", multipart.getKey());
            object.add("apply", multipart.getValue());
            multipartObj.add(object);
        });
        final JsonObject blockState = new JsonObject();
        blockState.add("multipart", multipartObj);
        return blockState;
    }
}
