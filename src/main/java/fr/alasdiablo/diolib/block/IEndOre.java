package fr.alasdiablo.diolib.block;

import fr.alasdiablo.diolib.config.ModConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Interface use by all The End Ore Block
 */
@SuppressWarnings("unused")
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
    default void angerEnderman(Player player, Level world, BlockPos pos, @Nullable Integer aggroRange, @Nullable Boolean isAggro) {
        isAggro = (isAggro == null) ? ModConfig.ENDERMAN_ANGER.canAnger() : isAggro;
        aggroRange = (aggroRange == null) ? ModConfig.ENDERMAN_ANGER.getAngerRange() : aggroRange;
        if (isAggro) {
            final int x = pos.getX(), y = pos.getY(), z = pos.getZ();
            List<EnderMan> list = world.getEntitiesOfClass(EnderMan.class,
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
