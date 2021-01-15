package fr.alasdiablo.diolib.gui;

import fr.alasdiablo.diolib.DiaboloLib;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import java.awt.Color;

public class GroundItemGroup extends ItemGroup {

    private final ItemStack icon;

    public GroundItemGroup(String label, ItemStack icon) {
        super(label);
        this.icon = icon;
        super.setBackgroundImageName("ground.png");
    }

    @Deprecated
    @Override
    public ItemGroup setBackgroundImageName(String texture) {
        DiaboloLib.logger.debug("setBackgroundImageName in OreItemGroup do nothing, please don't user it.");
        return this;
    }

    @Override
    public ItemStack createIcon() {
        return this.icon;
    }

    @Override
    public int getLabelColor() {
        return Color.BLACK.getRGB();
    }
}
