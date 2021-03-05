package fr.alasdiablo.diolib.block;

import fr.alasdiablo.diolib.config.ModConfig;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Interface use by all Nether Ore Block
 */
public interface INetherOre {

    /**
     * Default implementation of angerZombifiedPiglin (function use of make <i>ZombifiedPiglinEntity</i> attack <i>PlayerEntity</i>)
     *
     * @param player     Player who brake the block
     * @param world      The current world
     * @param pos        Block world position
     * @param aggroRange if null use value set by MobAngerConfig, aggro range with block as origine
     * @param isAggro    if null use value set by MobAngerConfig, enable or disable mob aggro
     */
    default void angerZombifiedPiglin(PlayerEntity player, World world, BlockPos pos, @Nullable Integer aggroRange, @Nullable Boolean isAggro) {
        isAggro = (isAggro == null) ? ModConfig.ZOMBIFIED_PIGLIN_ANGER.canAnger() : isAggro;
        aggroRange = (aggroRange == null) ? ModConfig.ZOMBIFIED_PIGLIN_ANGER.getAngerRange() : aggroRange;
        if (isAggro) {
            final int x = pos.getX(), y = pos.getY(), z = pos.getZ();
            List<ZombifiedPiglinEntity> list = world.getEntitiesWithinAABB(ZombifiedPiglinEntity.class,
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
