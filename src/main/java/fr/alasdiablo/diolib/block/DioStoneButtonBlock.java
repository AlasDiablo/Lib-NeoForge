package fr.alasdiablo.diolib.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.StoneButtonBlock;
import net.minecraft.block.material.Material;

public class DioStoneButtonBlock extends StoneButtonBlock {
    public DioStoneButtonBlock(String registryName) {
        super(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F));
        this.setRegistryName(registryName);
    }
}
