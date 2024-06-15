package fr.alasdiablo.diolib.api.block;

import fr.alasdiablo.diolib.config.DiaboloLibConfig;
import net.minecraft.core.Position;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

/**
 * Interface used by all The End Ore Block
 */
@SuppressWarnings("unused")
public interface AngerEnderman extends Anger {

    /**
     * Default implementation of anger (function use of make <i>EndermanEntity</i> attack <i>PlayerEntity</i>)
     * <p>
     * Use given position
     *
     * @param player     Player, who break the block
     * @param world      The current world
     * @param position   Block position
     * @param aggroRange if null use value set by MobAngerConfig, aggro range with block as origine
     * @param isAggro    if null use value set by MobAngerConfig, enable or disable mob aggro
     */
    default void anger(Player player, Level world, Position position, int aggroRange, boolean isAggro) {
        if (!isAggro) return;
        final float x = (float) position.x(), y = (float) position.y(), z = (float) position.z();
        List<EnderMan> list = world.getEntitiesOfClass(
                EnderMan.class,
                AABB.of(new BoundingBox(
                                Math.round(x - aggroRange),
                                Math.round(y - aggroRange),
                                Math.round(z - aggroRange),
                                Math.round(x + aggroRange + 1),
                                Math.round(y + aggroRange + 1),
                                Math.round(z + aggroRange + 1)
                        )
                )
        );
        list.forEach(e -> e.setTarget(player));
    }

    /**
     * Default implementation of anger (function use of make <i>EndermanEntity</i> attack <i>PlayerEntity</i>)
     * <p>
     * Use player position
     *
     * @param player     Player, who break the block
     * @param world      The current world
     * @param aggroRange if null use value set by MobAngerConfig, aggro range with block as origine
     * @param isAggro    if null use value set by MobAngerConfig, enable or disable mob aggro
     */
    default void anger(Player player, Level world, int aggroRange, boolean isAggro) {
        this.anger(player, world, player.position(), aggroRange, isAggro);
    }

    /**
     * Default implementation of anger (function use of make <i>EndermanEntity</i> attack <i>PlayerEntity</i>)
     * <p>
     * Use given position
     *
     * @param player   Player, who break the block
     * @param world    The current world
     * @param position Block position
     */
    default void anger(Player player, Level world, Vec3i position) {
        this.anger(
                player, world, new Vec3(position.getX(), position.getY(), position.getZ()), DiaboloLibConfig.ENDERMAN_ANGER.getAngerRange(),
                DiaboloLibConfig.ENDERMAN_ANGER.canAnger()
        );
    }

    /**
     * Default implementation of anger (function use of make <i>EndermanEntity</i> attack <i>PlayerEntity</i>)
     * <p>
     * Use given position
     *
     * @param player   Player, who break the block
     * @param world    The current world
     * @param position Entity position
     */
    default void anger(Player player, Level world, Position position) {
        this.anger(player, world, position, DiaboloLibConfig.ENDERMAN_ANGER.getAngerRange(), DiaboloLibConfig.ENDERMAN_ANGER.canAnger());
    }

    /**
     * Default implementation of anger (function use of make <i>EndermanEntity</i> attack <i>PlayerEntity</i>)
     * <p>
     * Use player position
     *
     * @param player Player, who break the block
     * @param world  The current world
     */
    default void anger(Player player, Level world) {
        this.anger(player, world, DiaboloLibConfig.ENDERMAN_ANGER.getAngerRange(), DiaboloLibConfig.ENDERMAN_ANGER.canAnger());
    }
}
