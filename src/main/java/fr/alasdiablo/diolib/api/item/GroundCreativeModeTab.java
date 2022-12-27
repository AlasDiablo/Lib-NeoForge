package fr.alasdiablo.diolib.api.item;

import fr.alasdiablo.diolib.DiaboloLib;
import fr.alasdiablo.diolib.api.util.DateRange;
import fr.alasdiablo.diolib.api.util.ResourceLocations;
import net.minecraft.world.item.CreativeModeTab;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

@SuppressWarnings("unused")
public class GroundCreativeModeTab {

    @Contract("_ -> param1")
    public static CreativeModeTab.@NotNull Builder createBaseBuilder(CreativeModeTab.@NotNull Builder builder) {
        builder.withLabelColor(Color.BLACK.getRGB());

        if (DateRange.IS_WINTER) {
            builder.withBackgroundLocation(
                    ResourceLocations.of(DiaboloLib.MOD_ID, "textures/gui/container/creative_inventory/ground_winter.png")
            );
            return builder;
        }

        if (DateRange.IS_APRIL_FIRST) {
            builder.withBackgroundLocation(
                    ResourceLocations.of(DiaboloLib.MOD_ID, "textures/gui/container/creative_inventory/ground_april.png")
            );
            return builder;
        }

        builder.withBackgroundLocation(
                ResourceLocations.of(DiaboloLib.MOD_ID, "textures/gui/container/creative_inventory/ground.png")
        );
        return builder;
    }
}
