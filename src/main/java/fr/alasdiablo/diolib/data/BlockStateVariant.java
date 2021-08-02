package fr.alasdiablo.diolib.data;

import com.google.gson.JsonObject;

public class BlockStateVariant {

    private final String key;
    private final String model;
    private final int x;
    private final int y;
    private final boolean uvlock;

    public BlockStateVariant(String key, String model, int x, int y, boolean uvlock) {
        this.key = key;
        this.model = model;
        this.x = x;
        this.y = y;
        this.uvlock = uvlock;
    }

    public static BlockStateVariant of(String key, String model, int x, int y, boolean uvlock) {
        return new BlockStateVariant(key, model, x, y, uvlock);
    }

    public String getKey() {
        return this.key;
    }

    public JsonObject getJson() {
        JsonObject json = new JsonObject();
        json.addProperty("model", this.model);
        if (x != 0) json.addProperty("x", this.x);
        if (y != 0) json.addProperty("y", this.y);
        if (uvlock) json.addProperty("x", true);
        return json;
    }
}
