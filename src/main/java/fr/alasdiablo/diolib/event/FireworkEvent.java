package fr.alasdiablo.diolib.event;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
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
import net.minecraftforge.eventbus.api.Event;
import org.apache.logging.log4j.message.FormattedMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Event Handler use of spawn a firework when an author of Janoeo Project join a world
 */
public class FireworkEvent implements IEvent {

    /**
     * Function use for spawn the firework
     *
     * @param player PlayerEntity use of get player position inside the world
     * @param world  Current world
     * @param star   The firework star element
     */
    private void generateFirework(PlayerEntity player, World world, CompoundNBT star, String playerType) {
        final ItemStack firework = new ItemStack(Items.FIREWORK_ROCKET);
        final CompoundNBT rocket = new CompoundNBT();
        final CompoundNBT fireworkCompound = firework.getOrCreateTag();
        rocket.putInt("Flight", 3);
        rocket.put("Explosions", Arrays.stream(new CompoundNBT[]{star}).collect(Collectors.toCollection(ListNBT::new)));
        fireworkCompound.put("Fireworks", rocket);
        DiaboloLib.logger.debug(new FormattedMessage("Spawning %s[%s] Firework.", playerType, player.getName().getString()));
        world.addEntity(new FireworkRocketEntity(world, player.prevPosX, player.prevPosY, player.prevPosZ, firework));
    }

    private String[] listOfContributor = null;

    private String[] getContributor() throws IOException {
        if (this.listOfContributor != null) return this.listOfContributor;
        final URL json = new URL("https://raw.githubusercontent.com/AlasDiablo/JANOEO-Doc/gh-pages/contributor.json");
        BufferedReader in = new BufferedReader(new InputStreamReader(json.openStream()));
        StringBuilder jsonStr = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) jsonStr.append(inputLine);
        in.close();
        this.listOfContributor = new Gson().fromJson(jsonStr.toString(), String[].class);
        for (String s : this.listOfContributor)
            DiaboloLib.logger.debug(new FormattedMessage("Contributor found: %s", s));
        return this.listOfContributor;
    }

    /**
     * Function use for handle the event
     *
     * @param event Instance of the event
     */
    @Override
    public void init(Event event) {
        PlayerEvent.PlayerLoggedInEvent playerLoggedInEvent = (PlayerEvent.PlayerLoggedInEvent) event;
        if (ModConfig.CONTRIBUTOR_FIREWORK.canContributorFirework()) {
            final PlayerEntity player = playerLoggedInEvent.getPlayer();
            final World world = player.world;
            switch (player.getName().getString()) {
                case "AlasDiablo": {
                    final CompoundNBT star = new CompoundNBT();
                    star.putIntArray("Colors", Collections.singletonList(15790320));
                    star.putIntArray("FadeColors", Collections.singletonList(11743532));
                    star.putBoolean("Flicker", true);
                    star.putBoolean("Trail", true);
                    star.putInt("Type", 1);
                    this.generateFirework(player, world, star, "Author");
                    break;
                }
                case "Safyrus": {
                    final CompoundNBT star = new CompoundNBT();
                    star.putIntArray("Colors", Lists.newArrayList(6719955, 15790320));
                    star.putIntArray("FadeColors", Lists.newArrayList(2437522, 11250603));
                    star.putBoolean("Flicker", true);
                    star.putBoolean("Trail", true);
                    star.putInt("Type", 2);
                    this.generateFirework(player, world, star, "Author");
                    break;
                }
            }
        }

        if (ModConfig.CONTRIBUTOR_FIREWORK.canContributorFirework()) {
            final PlayerEntity player = playerLoggedInEvent.getPlayer();
            final World world = player.world;
            try {
                if (Arrays.asList(this.getContributor()).contains(player.getName().getString())) {
                    final CompoundNBT star = new CompoundNBT();
                    star.putIntArray("Colors", Collections.singletonList(4312372));
                    star.putInt("Type", 4);
                    this.generateFirework(player, world, star, "Contributor");
                }
            } catch (IOException e) {
                DiaboloLib.logger.error(new FormattedMessage("Error during ContributorEvent event execution: %s", e.getMessage()));
            }
        }
    }
}
