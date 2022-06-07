package fr.alasdiablo.diolib.api.block;

import fr.alasdiablo.diolib.config.DiaboloLibConfig;
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
@Deprecated(since = "4.5.26", forRemoval = true)
public interface IEndOre extends AngerEnderman {

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
        isAggro    = (isAggro == null) ? DiaboloLibConfig.ENDERMAN_ANGER.canAnger() : isAggro;
        aggroRange = (aggroRange == null) ? DiaboloLibConfig.ENDERMAN_ANGER.getAngerRange() : aggroRange;
        this.anger(player, world, pos, aggroRange, isAggro);
    }
}
