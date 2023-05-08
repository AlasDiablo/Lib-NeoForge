package fr.alasdiablo.diolib.api.block;

import net.minecraft.core.Position;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("unused")
@ParametersAreNonnullByDefault
public interface Anger {

    /**
     * @param player     Player, who break the block
     * @param world      The current world
     * @param position   Entity position
     * @param aggroRange if null use value set by MobAngerConfig, aggro range with block as origine
     * @param isAggro    if null use value set by MobAngerConfig, enable or disable mob aggro
     */
    void anger(Player player, Level world, Position position, int aggroRange, boolean isAggro);

    /**
     * @param player     Player, who break the block
     * @param world      The current world
     * @param aggroRange if null use value set by MobAngerConfig, aggro range with block as origine
     * @param isAggro    if null use value set by MobAngerConfig, enable or disable mob aggro
     */
    void anger(Player player, Level world, int aggroRange, boolean isAggro);

    /**
     * @param player   Player, who break the block
     * @param world    The current world
     * @param position Block position
     */
    void anger(Player player, Level world, Vec3i position);

    /**
     * @param player   Player, who break the block
     * @param world    The current world
     * @param position Entity position
     */
    void anger(Player player, Level world, Position position);

    /**
     * @param player Player, who break the block
     * @param world  The current world
     */
    void anger(Player player, Level world);
}
