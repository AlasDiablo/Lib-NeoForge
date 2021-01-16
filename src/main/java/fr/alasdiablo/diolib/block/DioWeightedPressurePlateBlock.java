package fr.alasdiablo.diolib.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.WeightedPressurePlateBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class DioWeightedPressurePlateBlock extends WeightedPressurePlateBlock {
    public DioWeightedPressurePlateBlock(int maxWeight, MaterialColor color, String registryName) {
        super(maxWeight, AbstractBlock.Properties.create(Material.IRON, color).setRequiresTool().doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
        this.setRegistryName(registryName);
    }
}
