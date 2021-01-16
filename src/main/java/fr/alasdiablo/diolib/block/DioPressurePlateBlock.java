package fr.alasdiablo.diolib.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class DioPressurePlateBlock extends PressurePlateBlock {
    public DioPressurePlateBlock(Sensitivity sensitivity, MaterialColor color, String registryName) {
        super(sensitivity, AbstractBlock.Properties.create(Material.WOOD, color).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
        this.setRegistryName(registryName);
    }
}
