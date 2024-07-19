package fr.alasdiablo.mods.lib.api.item;

import fr.alasdiablo.mods.lib.Constants;
import fr.alasdiablo.mods.lib.api.util.DateRange;
import fr.alasdiablo.mods.lib.api.util.ResourceLocations;
import net.minecraft.world.item.CreativeModeTab;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

@SuppressWarnings("unused")
public class GroundCreativeModeTab {

    @Contract("_ -> param1")
    public static CreativeModeTab.@NotNull Builder createBaseBuilder(CreativeModeTab.@NotNull Builder builder) {
        if (DateRange.IS_WINTER) {
            builder.backgroundTexture(
                    ResourceLocations.of(Constants.MOD_ID, "textures/gui/container/creative_inventory/ground_winter.png")
            );
            return builder;
        }

        if (DateRange.IS_APRIL_FIRST) {
            builder.backgroundTexture(
                    ResourceLocations.of(Constants.MOD_ID, "textures/gui/container/creative_inventory/ground_april.png")
            );
            return builder;
        }

        builder.backgroundTexture(
                ResourceLocations.of(Constants.MOD_ID, "textures/gui/container/creative_inventory/ground.png")
        );
        return builder;
    }
}
