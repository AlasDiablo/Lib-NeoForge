package fr.alasdiablo.diolib.gui;

import fr.alasdiablo.diolib.DiaboloLib;
import fr.alasdiablo.diolib.util.DateRange;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.Color;

@SuppressWarnings("unused")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public abstract class GroundItemGroup extends CreativeModeTab {

    public GroundItemGroup(String label) {
        super(label);
        if (DateRange.IS_WINTER) {
            super.setBackgroundImage(new ResourceLocation(DiaboloLib.MOD_ID, "textures/gui/container/creative_inventory/ground_winter.png"));
        } else {
            super.setBackgroundImage(new ResourceLocation(DiaboloLib.MOD_ID, "textures/gui/container/creative_inventory/ground.png"));
        }
        if (DateRange.IS_APRIL_FIRST)
            super.setBackgroundImage(new ResourceLocation(DiaboloLib.MOD_ID, "textures/gui/container/creative_inventory/ground_april.png"));
    }

    @Override
    public CreativeModeTab setBackgroundImage(ResourceLocation texture) {
        DiaboloLib.logger.warn("setBackgroundImageName in OreItemGroup do nothing, please don't user it.");
        return this;
    }

    @Override
    public int getLabelColor() {
        return Color.BLACK.getRGB();
    }
}
