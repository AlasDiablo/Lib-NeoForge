package fr.alasdiablo.diolib.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.Color;

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
     * Register color for an block
     * @param blockColor color of the block
     * @param block item who get the blockColor
     */
    public static void registerBlockColor(IBlockColor blockColor, Block block) {
        BLOCK_COLORS.register(blockColor, block);
    }

    /**
     * Register color for an item
     * @param itemColor color of the item
     * @param item item who get the itemColor
     */
    public static void registerItemColor(IItemColor itemColor, IItemProvider item) {
        ITEM_COLORS.register(itemColor, item);
    }

    /**
     * Register color for a basic Foliage Color
     * @param block target block
     * @param color target color
     */
    public static void registerFoliageColor(Block block, Color color) {
        registerBlockColor(
                (state, world, pos, tintIndex) -> color.getRGB(),
                block
        );
        registerItemColor(
                (stack, tintIndex) -> {
                    BlockState blockState = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
                    return BLOCK_COLORS.getColor(blockState, null, null, tintIndex);
                },
                block
        );
    }

    /**
     * Register color for a basic Foliage Color
     * @param block target block
     */
    public static void registerFoliageColor(Block block) {
        registerBlockColor(
                (state, world, pos, tintIndex) -> {
                    if (world != null && pos != null)
                        return BiomeColors.getFoliageColor(world, pos);
                    else
                        return FoliageColors.getDefault();
                },
                block
        );
        registerItemColor(
                (stack, tintIndex) -> {
                    BlockState blockState = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
                    return BLOCK_COLORS.getColor(blockState, null, null, tintIndex);
                },
                block
        );
    }

}
