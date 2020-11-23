package fr.alasdiablo.diabolo.block;

import fr.alasdiablo.diabolo.config.ModConfig;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Interface use by all The End Ore Block
 */
public interface IEndOre {

    /**
     * Default implementation of angerEnderman (function use of make <i>EndermanEntity</i> attack <i>PlayerEntity</i>)
     *
     * @param player     Player who brake the block
     * @param world      The current world
     * @param pos        Block world position
     * @param aggroRange if null use value set by MobAngerConfig, aggro range with block as origine
     * @param isAggro    if null use value set by MobAngerConfig, enable or disable mob aggro
     */
    default void angerEnderman(PlayerEntity player, World world, BlockPos pos, @Nullable Integer aggroRange, @Nullable Boolean isAggro) {
        isAggro = (isAggro == null) ? ModConfig.MobAngerConfig.ZOMBIFIED_PIGLIN_ANGER.get() : isAggro;
        aggroRange = (aggroRange == null) ? ModConfig.MobAngerConfig.ZOMBIFIED_PIGLIN_ANGER_RANGE.get() : aggroRange;
        if (isAggro) {
            final int x = pos.getX(), y = pos.getY(), z = pos.getZ();
            List<EndermanEntity> list = world.getEntitiesWithinAABB(EndermanEntity.class,
                    AxisAlignedBB.toImmutable(MutableBoundingBox.createProper(
                            x - aggroRange,
                            y - aggroRange,
                            z - aggroRange,
                            x + aggroRange + 1,
                            y + aggroRange + 1,
                            z + aggroRange + 1))
            );
            list.forEach(e -> e.setAttackTarget(player));
        }
    }
}
