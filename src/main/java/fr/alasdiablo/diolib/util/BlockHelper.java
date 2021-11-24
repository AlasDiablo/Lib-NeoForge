package fr.alasdiablo.diolib.util;

import com.google.common.collect.Maps;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;

@SuppressWarnings("unused")
public class BlockHelper {

    public static final  int       PLANKS_ENCOURAGEMENT     = 5;
    public static final  int       PLANKS_FLAMMABILITY      = 20;
    public static final  int       SLAB_ENCOURAGEMENT       = 5;
    public static final  int       SLAB_FLAMMABILITY        = 20;
    public static final  int       FENCE_GATE_ENCOURAGEMENT = 5;
    public static final  int       FENCE_GATE_FLAMMABILITY  = 20;
    public static final  int       FENCE_ENCOURAGEMENT      = 5;
    public static final  int       FENCE_FLAMMABILITY       = 20;
    public static final  int       STAIRS_ENCOURAGEMENT     = 5;
    public static final  int       STAIRS_FLAMMABILITY      = 20;
    public static final  int       LOG_ENCOURAGEMENT        = 5;
    public static final  int       LOG_FLAMMABILITY         = 5;
    public static final  int       WOOD_ENCOURAGEMENT       = 5;
    public static final  int       WOOD_FLAMMABILITY        = 5;
    public static final  int       LEAVES_ENCOURAGEMENT     = 30;
    public static final  int       LEAVES_FLAMMABILITY      = 60;
    public static final  int       PLANT_ENCOURAGEMENT      = 60;
    public static final  int       PLANT_FLAMMABILITY       = 100;
    public static final  int       WOOL_ENCOURAGEMENT       = 30;
    public static final  int       WOOL_FLAMMABILITY        = 60;
    public static final  int       CARPET_ENCOURAGEMENT     = 60;
    public static final  int       CARPET_FLAMMABILITY      = 20;
    private static final FireBlock fireBlock                = (FireBlock) Blocks.FIRE;

    public static Block setFlammability(Block block, int encouragement, int flammability) {
        fireBlock.setFlammable(block, encouragement, flammability);
        return block;
    }

    public static void createStrippablesBlock(Block source, Block target) {
        AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
        AxeItem.STRIPPABLES.put(source, target);
    }
}
