package fr.alasdiablo.diabolo.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Helper use for register block and item color quickly
 */
@OnlyIn(Dist.CLIENT)
public class ColorsHelper {

    private static final BlockColors BLOCK_COLORS;
    private static final ItemColors ITEM_COLORS;
    static {
        final Minecraft minecraft = Minecraft.getInstance();
        BLOCK_COLORS = minecraft.getBlockColors();
        ITEM_COLORS = minecraft.getItemColors();
    }

    /**
     * Register color for a basic Foliage Color
     * @param block target block
     */
    public static void registerFoliageColor(Block block) {
        BLOCK_COLORS.register(
                (state, world, pos, tintIndex) -> {
                    if (world != null && pos != null)
                        return BiomeColors.getFoliageColor(world, pos);
                    else
                        return FoliageColors.getDefault();
                },
                block
        );
        ITEM_COLORS.register(
                (stack, tintIndex) -> {
                    BlockState blockState = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
                    return BLOCK_COLORS.getColor(blockState, null, null, tintIndex);
                },
                block
        );
    }

}
