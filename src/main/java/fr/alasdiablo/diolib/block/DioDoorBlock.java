package fr.alasdiablo.diolib.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class DioDoorBlock extends DoorBlock {
    public DioDoorBlock(MaterialColor color, String registryName) {
        super(AbstractBlock.Properties.create(Material.WOOD, color).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid());
        this.setRegistryName(registryName);
    }
}
