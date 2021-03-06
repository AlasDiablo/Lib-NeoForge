package fr.alasdiablo.diolib.gui;

import fr.alasdiablo.diolib.DiaboloLib;
import fr.alasdiablo.diolib.util.DateRange;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

import java.awt.Color;

public abstract class GroundItemGroup extends ItemGroup {

    public GroundItemGroup(String label) {
        super(label);
        if (DateRange.IS_WINTER) {
            super.setBackgroundImage(new ResourceLocation(DiaboloLib.MOD_ID, "ground_winter.png"));
        } else {
            super.setBackgroundImage(new ResourceLocation(DiaboloLib.MOD_ID, "ground.png"));
        }
        if (DateRange.IS_APRIL_FIRST)
            super.setBackgroundImage(new ResourceLocation(DiaboloLib.MOD_ID, "ground_april.png"));
    }

    @Deprecated
    @Override
    public ItemGroup setBackgroundImageName(String texture) {
        DiaboloLib.logger.debug("setBackgroundImageName in OreItemGroup do nothing, please don't user it.");
        return this;
    }

    @Override
    public ItemGroup setBackgroundImage(ResourceLocation texture) {
        DiaboloLib.logger.debug("setBackgroundImageName in OreItemGroup do nothing, please don't user it.");
        return this;
    }

    @Override
    public int getLabelColor() {
        return Color.BLACK.getRGB();
    }
}
