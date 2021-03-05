package fr.alasdiablo.diolib.event;

import com.google.common.collect.Lists;
import fr.alasdiablo.diolib.DiaboloLib;
import fr.alasdiablo.diolib.config.ModConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.message.FormattedMessage;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Event Handler use of spawn a firework when an author of Janoeo Project join a world
 */
@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = DiaboloLib.MOD_ID)
public class CreatorsEvent {

    /**
     * Function use for handle the event
     *
     * @param event Instance of the event
     */
    @SubscribeEvent
    public static void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (ModConfig.CONTRIBUTOR_FIREWORK.canContributorFirework()) {
            final PlayerEntity player = event.getPlayer();
            final World world = player.world;
            switch (player.getName().getString()) {
                case "AlasDiablo": {
                    final CompoundNBT star = new CompoundNBT();
                    star.putIntArray("Colors", Collections.singletonList(15790320));
                    star.putIntArray("FadeColors", Collections.singletonList(11743532));
                    star.putBoolean("Flicker", true);
                    star.putBoolean("Trail", true);
                    star.putInt("Type", 1);
                    generateFirework(player, world, star, "Author");
                    break;
                }
                case "Safyrus": {
                    final CompoundNBT star = new CompoundNBT();
                    star.putIntArray("Colors", Lists.newArrayList(6719955, 15790320));
                    star.putIntArray("FadeColors", Lists.newArrayList(2437522, 11250603));
                    star.putBoolean("Flicker", true);
                    star.putBoolean("Trail", true);
                    star.putInt("Type", 2);
                    generateFirework(player, world, star, "Author");
                    break;
                }
            }
        }
    }

    /**
     * Function use for spawn the firework
     *
     * @param player PlayerEntity use of get player position inside the world
     * @param world  Current world
     * @param star   The firework star element
     */
    static void generateFirework(PlayerEntity player, World world, CompoundNBT star, String playerType) {
        final ItemStack firework = new ItemStack(Items.FIREWORK_ROCKET);
        final CompoundNBT rocket = new CompoundNBT();
        final CompoundNBT fireworkCompound = firework.getOrCreateTag();
        rocket.putInt("Flight", 3);
        rocket.put("Explosions", Arrays.stream(new CompoundNBT[]{star}).collect(Collectors.toCollection(ListNBT::new)));
        fireworkCompound.put("Fireworks", rocket);
        DiaboloLib.logger.debug(new FormattedMessage("Spawning %s[%s] Firework.", playerType, player.getName().getString()));
        world.addEntity(new FireworkRocketEntity(world, player.prevPosX, player.prevPosY, player.prevPosZ, firework));
    }
}
