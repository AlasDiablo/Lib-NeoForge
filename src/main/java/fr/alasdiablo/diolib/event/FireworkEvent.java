package fr.alasdiablo.diolib.event;

import com.google.common.collect.Lists;
import com.google.gson.*;
import fr.alasdiablo.diolib.DiaboloLib;
import fr.alasdiablo.diolib.config.ModConfig;
import fr.alasdiablo.diolib.lang.ImmutablePair;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import org.apache.logging.log4j.message.FormattedMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
    private void generateFirework(Player player, Level world, CompoundTag star, String playerType) {
        final ItemStack firework = new ItemStack(Items.FIREWORK_ROCKET);
        final CompoundTag rocket = new CompoundTag();
        final CompoundTag fireworkCompound = firework.getOrCreateTag();
        rocket.putInt("Flight", 3);
        rocket.put("Explosions", Arrays.stream(new CompoundTag[]{star}).collect(Collectors.toCollection(ListTag::new)));
        fireworkCompound.put("Fireworks", rocket);
        DiaboloLib.logger.debug(new FormattedMessage("Spawning %s[%s] Firework.", playerType, player.getName().getString()));
        world.addFreshEntity(new FireworkRocketEntity(world, player.xOld, player.yOld, player.zOld, firework));
    }

    private Map<String, ImmutablePair<String, String>> listOfContributor = null;

    private Map<String, ImmutablePair<String, String>> getContributor() throws IOException {
        if (this.listOfContributor != null) return this.listOfContributor;

        final URL json = new URL("https://raw.githubusercontent.com/Janoeo/DiaboloLib/1.16-V2/contributors.json");
        BufferedReader in = new BufferedReader(new InputStreamReader(json.openStream()));
        StringBuilder jsonStr = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) jsonStr.append(inputLine);
        in.close();

        JsonArray contributors = new JsonParser().parse(jsonStr.toString()).getAsJsonArray();
        this.listOfContributor = new HashMap<>();
        contributors.forEach(contributor -> {
            JsonObject contributorObject = contributor.getAsJsonObject();
            String UUID = contributorObject.get("uuid").getAsString();
            String name = contributorObject.get("name_when_add").getAsString();
            String type = contributorObject.get("type").getAsString();
            listOfContributor.put(UUID, new ImmutablePair<>(name, type));
            DiaboloLib.logger.debug(new FormattedMessage("Contributor found: %s/%s/%s", UUID, name, type));
        });

        return this.listOfContributor;
    }

    private int getColor(String contributionType) {
        return switch (contributionType) {
            case "code" -> 6719955; // light blue
            case "test" -> 4312372; // lime
            default -> 15790320;    // white
        };
    }

    private static final String ALASDIABLO_UUID = "e7956203-8c12-429e-9956-99775b8199ac";
    private static final String SAFYRUS_UUID = "b4172f45-a45c-4b35-ac5f-2e9d57835154";
    /**
     * Function use for handle the event
     *
     * @param event Instance of the event
     */
    @Override
    public void init(Event event) {
        PlayerEvent.PlayerLoggedInEvent playerLoggedInEvent = (PlayerEvent.PlayerLoggedInEvent) event;
        if (ModConfig.CONTRIBUTOR_FIREWORK.canContributorFirework()) {
            final Player player = playerLoggedInEvent.getPlayer();
            final Level world = player.level;
            switch (player.getStringUUID()) {
                case ALASDIABLO_UUID -> {
                    final CompoundTag star = new CompoundTag();
                    star.putIntArray("Colors", Collections.singletonList(15790320));
                    star.putIntArray("FadeColors", Collections.singletonList(11743532));
                    star.putBoolean("Flicker", true);
                    star.putBoolean("Trail", true);
                    star.putInt("Type", 1);
                    this.generateFirework(player, world, star, "Author");
                }
                case SAFYRUS_UUID -> {
                    final CompoundTag star = new CompoundTag();
                    star.putIntArray("Colors", Lists.newArrayList(6719955, 15790320));
                    star.putIntArray("FadeColors", Lists.newArrayList(2437522, 11250603));
                    star.putBoolean("Flicker", true);
                    star.putBoolean("Trail", true);
                    star.putInt("Type", 2);
                    this.generateFirework(player, world, star, "Author");
                }
            }
        }

        if (ModConfig.CONTRIBUTOR_FIREWORK.canContributorFirework()) {
            final Player player = playerLoggedInEvent.getPlayer();
            final Level world = player.level;
            try {
                final Map<String, ImmutablePair<String, String>> contributors = this.getContributor();
                final String UUID = player.getStringUUID();
                if (contributors.containsKey(UUID)) {
                    final String contributionType = contributors.get(UUID).value();
                    final CompoundTag star = new CompoundTag();
                    star.putIntArray("Colors", Collections.singletonList(getColor(contributionType)));
                    star.putInt("Type", 4);
                    this.generateFirework(player, world, star, "Contributor");
                }
            } catch (IOException e) {
                DiaboloLib.logger.error(new FormattedMessage("Error during ContributorEvent event execution: %s", e.getMessage()));
            }
        }
    }
}
