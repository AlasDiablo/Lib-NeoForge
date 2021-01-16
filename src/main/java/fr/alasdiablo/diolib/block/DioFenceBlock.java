package fr.alasdiablo.diolib.block;

import net.minecraft.block.FenceBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class DioFenceBlock extends FenceBlock {
    public DioFenceBlock(MaterialColor color, String registryName) {
        super(Properties.create(Material.WOOD, color).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
        this.setRegistryName(registryName);
    }
}
