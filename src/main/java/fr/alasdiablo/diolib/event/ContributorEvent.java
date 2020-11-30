package fr.alasdiablo.diolib.event;

import com.google.gson.Gson;
import fr.alasdiablo.diolib.DiaboloLib;
import fr.alasdiablo.diolib.config.ModConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.message.FormattedMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;

/**
 * Event Handler use of spawn a firework when an contributor(Bug Hunter and Code Writer) of Janoeo Project join a world
 */
public class ContributorEvent {

    /**
     * Function use for handle the event
     *
     * @param event Instance of the event
     */
    @SubscribeEvent
    public static void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (ModConfig.DefaultConfig.AUTHOR_LOGGING_EVENT.get()) {
            final PlayerEntity player = event.getPlayer();
            final World world = player.world;
            try {
                if (Arrays.asList(getContributor()).contains(player.getName().getString())) {
                    final CompoundNBT star = new CompoundNBT();
                    star.putIntArray("Colors", Collections.singletonList(4312372));
                    star.putInt("Type", 4);
                    CreatorsEvent.generateFirework(player, world, star, "Contributor");
                }
            } catch (IOException e) {
                DiaboloLib.logger.error(new FormattedMessage("Error during ContributorEvent event execution: %s", e.getMessage()));
            }
        }
    }

    private static String[] listOfContributor = null;

    private static String[] getContributor() throws IOException {
        if (listOfContributor != null) return listOfContributor;
        URL oracle = new URL("https://raw.githubusercontent.com/AlasDiablo/JANOEO-Doc/gh-pages/contributor.json");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));
        StringBuilder jsonStr = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) jsonStr.append(inputLine);
        in.close();
        listOfContributor = new Gson().fromJson(jsonStr.toString(), String[].class);
        for (String s : listOfContributor)
            DiaboloLib.logger.debug(new FormattedMessage("Contributor found: %s", s));
        return listOfContributor;
    }
}
