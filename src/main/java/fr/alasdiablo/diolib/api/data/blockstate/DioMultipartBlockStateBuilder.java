package fr.alasdiablo.diolib.api.data.blockstate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.alasdiablo.diolib.util.ImmutablePair;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.IGeneratedBlockstate;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class DioMultipartBlockStateBuilder implements IGeneratedBlockstate {

    private final List<ImmutablePair<JsonObject, JsonObject>> multipart;

    public DioMultipartBlockStateBuilder() {
        this.multipart = new ArrayList<>();
    }

    public void addMultipart(@NotNull ResourceLocation modelPathIn) {
        JsonObject apply = new JsonObject();
        apply.addProperty("model", modelPathIn.toString());
        this.multipart.add(new ImmutablePair<>(null, apply));
    }

    public void addMultipart(@NotNull ResourceLocation modelPathIn, boolean uvLockIn) {
        JsonObject apply = new JsonObject();
        apply.addProperty("model", modelPathIn.toString());
        if (uvLockIn) apply.addProperty("uvlock", true);
        this.multipart.add(new ImmutablePair<>(null, apply));
    }

    public void addMultipart(@NotNull ResourceLocation modelPathIn, boolean uvLockIn, int xIn, int yIn) {
        JsonObject apply = new JsonObject();
        apply.addProperty("model", modelPathIn.toString());
        if (uvLockIn) apply.addProperty("uvlock", true);
        if (xIn != 0) apply.addProperty("x", xIn);
        if (yIn != 0) apply.addProperty("y", yIn);
        this.multipart.add(new ImmutablePair<>(null, apply));
    }

    public void addMultipart(@NotNull ResourceLocation modelPathIn, boolean north, boolean east, boolean south, boolean west) {
        JsonObject when  = new JsonObject();
        JsonObject apply = new JsonObject();
        this.handleCardinalPoint(when, north, east, south, west);
        apply.addProperty("model", modelPathIn.toString());
        this.multipart.add(new ImmutablePair<>(when, apply));
    }

    public void addMultipart(@NotNull ResourceLocation modelPathIn, boolean uvLockIn, boolean north, boolean east, boolean south, boolean west) {
        JsonObject when  = new JsonObject();
        JsonObject apply = new JsonObject();
        this.handleCardinalPoint(when, north, east, south, west);
        apply.addProperty("model", modelPathIn.toString());
        if (uvLockIn) apply.addProperty("uvlock", true);
        this.multipart.add(new ImmutablePair<>(when, apply));
    }

    public void addMultipart(
            @NotNull ResourceLocation modelPathIn, boolean uvLockIn, int xIn, int yIn, boolean north, boolean east, boolean south, boolean west
    ) {
        JsonObject when  = new JsonObject();
        JsonObject apply = new JsonObject();
        this.handleCardinalPoint(when, north, east, south, west);
        apply.addProperty("model", modelPathIn.toString());
        if (uvLockIn) apply.addProperty("uvlock", true);
        if (xIn != 0) apply.addProperty("x", xIn);
        if (yIn != 0) apply.addProperty("y", yIn);
        this.multipart.add(new ImmutablePair<>(when, apply));
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
        this.multipart.forEach(multipart -> {
            JsonObject object = new JsonObject();
            if (multipart.key() != null) object.add("when", multipart.key());
            object.add("apply", multipart.value());
            multipartObj.add(object);
        });
        final JsonObject blockState = new JsonObject();
        blockState.add("multipart", multipartObj);
        return blockState;
    }
}
