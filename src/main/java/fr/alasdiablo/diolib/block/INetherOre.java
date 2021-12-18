package fr.alasdiablo.diolib.block;

import fr.alasdiablo.diolib.config.DiaboloLibConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Interface use by all Nether Ore Block
 */
@SuppressWarnings("unused")
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
    default void angerZombifiedPiglin(Player player, Level world, BlockPos pos, @Nullable Integer aggroRange, @Nullable Boolean isAggro) {
        isAggro    = (isAggro == null) ? DiaboloLibConfig.ZOMBIFIED_PIGLIN_ANGER.canAnger() : isAggro;
        aggroRange = (aggroRange == null) ? DiaboloLibConfig.ZOMBIFIED_PIGLIN_ANGER.getAngerRange() : aggroRange;
        if (isAggro) {
            final int x = pos.getX(), y = pos.getY(), z = pos.getZ();
            List<ZombifiedPiglin> list = world.getEntitiesOfClass(
                    ZombifiedPiglin.class,
                    AABB.of(new BoundingBox(
                            x - aggroRange,
                            y - aggroRange,
                            z - aggroRange,
                            x + aggroRange + 1,
                            y + aggroRange + 1,
                                    z + aggroRange + 1
                            )
                    )
            );
            list.forEach(e -> e.setTarget(player));
        }
    }
}
