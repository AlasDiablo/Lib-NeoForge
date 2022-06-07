package fr.alasdiablo.diolib.api.block;

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
@Deprecated(since = "4.5.26", forRemoval = true)
public interface INetherOre extends AngerZombifiedPiglin {

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
        this.anger(player, world, pos, aggroRange, isAggro);
    }
}
