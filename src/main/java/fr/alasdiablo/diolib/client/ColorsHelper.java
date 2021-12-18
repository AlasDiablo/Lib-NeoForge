package fr.alasdiablo.diolib.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;

/**
 * Helper use for register block and item color quickly
 */
@SuppressWarnings("unused")
@OnlyIn(Dist.CLIENT)
public class ColorsHelper {

    private static final BlockColors BLOCK_COLORS;
    private static final ItemColors  ITEM_COLORS;

    static {
        final Minecraft minecraft = Minecraft.getInstance();
        BLOCK_COLORS = minecraft.getBlockColors();
        ITEM_COLORS  = minecraft.getItemColors();
    }

    /**
     * Register color for an block
     *
     * @param blockColor color of the block
     * @param block      item who get the blockColor
     */
    public static void registerBlockColor(BlockColor blockColor, Block block) {
        BLOCK_COLORS.register(blockColor, block);
    }

    /**
     * Register color for an item
     *
     * @param itemColor color of the item
     * @param item      item who get the itemColor
     */
    public static void registerItemColor(ItemColor itemColor, ItemLike item) {
        ITEM_COLORS.register(itemColor, item);
    }

    /**
     * Register color for a basic Foliage Color
     *
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
                    BlockState blockState = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
                    return BLOCK_COLORS.getColor(blockState, null, null, tintIndex);
                },
                block
        );
    }

    /**
     * Register color for a basic Foliage Color
     *
     * @param block target block
     */
    public static void registerFoliageColor(Block block) {
        registerBlockColor(
                (state, world, pos, tintIndex) -> {
                    if (world != null && pos != null)
                        return BiomeColors.getAverageFoliageColor(world, pos);
                    else
                        return FoliageColor.getDefaultColor();
                },
                block
        );
        registerItemColor(
                (stack, tintIndex) -> {
                    BlockState blockState = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
                    return BLOCK_COLORS.getColor(blockState, null, null, tintIndex);
                },
                block
        );
    }

}
