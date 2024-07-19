package fr.alasdiablo.mods.lib.api.item.armor;

import fr.alasdiablo.mods.lib.api.util.ResourceLocations;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * This record is use as a registry name provider use to create each registry name of an armor set
 *
 * @param modId ID of the mod used to create the domaine of the ResourceLocation
 * @param name  Actual armor name use to create the complete registry name
 */
@SuppressWarnings("unused")
public record ArmorItemRegistryName(String modId, String name) {

    /**
     * Create the registry name with the given arguments
     *
     * @return The registry name of the helmet
     */
    @Contract(" -> new")
    public @NotNull ResourceLocation getHelmet() {
        return ResourceLocations.of(this.modId, this.name + "_helmet");
    }

    public @NotNull String getHelmetName() {
        return this.name + "_helmet";
    }

    /**
     * Create the registry name with the given arguments
     *
     * @return The registry name of the chestplate
     */
    @Contract(" -> new")
    public @NotNull ResourceLocation getChestplate() {
        return ResourceLocations.of(this.modId, this.name + "_chestplate");
    }

    public @NotNull String getChestplateName() {
        return this.name + "_chestplate";
    }

    /**
     * Create the registry name with the given arguments
     *
     * @return The registry name of the leggings
     */
    @Contract(" -> new")
    public @NotNull ResourceLocation getLeggings() {
        return ResourceLocations.of(this.modId, this.name + "_leggings");
    }

    public @NotNull String getLeggingsName() {
        return this.name + "_leggings";
    }

    /**
     * Create the registry name with the given arguments
     *
     * @return The registry name of the boots
     */
    @Contract(" -> new")
    public @NotNull ResourceLocation getBoots() {
        return ResourceLocations.of(this.modId, this.name + "_boots");
    }

    public @NotNull String getBootsName() {
        return this.name + "_boots";
    }
}
