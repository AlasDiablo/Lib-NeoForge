package fr.alasdiablo.diolib.api.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.RedStoneOreBlock;
import org.jetbrains.annotations.NotNull;

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

    /**
     * Helper use to set custom encouragement and flammability to a block
     *
     * @param block         Target block
     * @param encouragement New encouragement
     * @param flammability  New flammability
     *
     * @return Target block
     */
    public static Block setFlammability(@NotNull Block block, int encouragement, int flammability) {
        fireBlock.setFlammable(block, encouragement, flammability);
        return block;
    }

    /**
     * Halper use to add a block to the strippables block create via a axe
     *
     * @param source Source block (block who get the strip action)
     * @param target Result block
     */
    public static void createStrippablesBlock(@NotNull Block source, @NotNull Block target) {
        if (AxeItem.STRIPPABLES instanceof ImmutableMap) {
            AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
        }
        AxeItem.STRIPPABLES.put(source, target);
    }

    /**
     * Helper use to spawn redstone particles
     *
     * @param level The current level
     * @param pos   The spawn position of the particles
     */
    public static void redStoneOreParticles(@NotNull Level level, @NotNull BlockPos pos) {
        RedStoneOreBlock.spawnParticles(level, pos);
    }
}
