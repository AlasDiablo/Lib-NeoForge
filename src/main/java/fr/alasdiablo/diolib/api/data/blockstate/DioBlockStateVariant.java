package fr.alasdiablo.diolib.api.data.blockstate;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Class used to create single blockstate variant
 */
public record DioBlockStateVariant(String key, String model, int x, int y, boolean uvlock) {

    /**
     * Static factory of BlockStateVariant
     *
     * @param key    Name of the state
     * @param model  Model use by the state
     * @param x      Rotation on x of the state
     * @param y      Rotation on y of the state
     * @param uvlock UVLock of the state
     *
     * @return Instance of a BlockStateVariant
     */
    @Contract("_, _, _, _, _ -> new")
    public static @NotNull DioBlockStateVariant of(String key, String model, int x, int y, boolean uvlock) {
        return new DioBlockStateVariant(key, model, x, y, uvlock);
    }

    /**
     * Create the json of the blockstate variant
     *
     * @return Json representation of a blockstate variant
     */
    public @NotNull JsonObject json() {
        var variant = new JsonObject();
        // Set model
        variant.addProperty("model", model);
        // Add rotation on x
        if (this.x != 0) {
            variant.addProperty("x", this.x);
        }
        // Add rotation on y
        if (this.y != 0) {
            variant.addProperty("y", this.y);
        }
        // Add uvlock
        if (this.uvlock) {
            variant.addProperty("uvlock", true);
        }
        return variant;
    }
}
