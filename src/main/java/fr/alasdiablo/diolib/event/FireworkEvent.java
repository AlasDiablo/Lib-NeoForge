package fr.alasdiablo.diolib.event;

import com.google.common.collect.Lists;
import com.google.gson.JsonParser;
import fr.alasdiablo.diolib.DiaboloLib;
import fr.alasdiablo.diolib.config.DiaboloLibConfig;
import fr.alasdiablo.diolib.util.ImmutablePair;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.apache.logging.log4j.message.FormattedMessage;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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
 * Event Handler use of spawn a firework when an author of Janoeo Project joins a world
 */
public class FireworkEvent implements IEvent<PlayerEvent.PlayerLoggedInEvent> {

    public static final FireworkEvent FIREWORK_EVENT = new FireworkEvent();

    private static final String                                     ALASDIABLO_UUID = "e7956203-8c12-429e-9956-99775b8199ac";
    private static final String                                     SAFYRUS_UUID    = "b4172f45-a45c-4b35-ac5f-2e9d57835154";
    private static final String                                     SMARTZI_UUID    = "bc4ee8d8-88a7-4e95-b1e8-d9932b80d1e8";
    private final        Map<String, ImmutablePair<String, String>> listOfContributor;

    public FireworkEvent() {
        this.listOfContributor = new HashMap<>();

        try {
            var jsonUrl        = new URL("https://raw.githubusercontent.com/Janoeo/DiaboloLib/master/contributors.json");
            var bufferedReader = new BufferedReader(new InputStreamReader(jsonUrl.openStream()));

            var contributors = JsonParser.parseReader(bufferedReader).getAsJsonArray();
            contributors.forEach(contributor -> {
                var contributorObject = contributor.getAsJsonObject();
                var UUID              = contributorObject.get("uuid").getAsString();
                var name              = contributorObject.get("name_when_add").getAsString();
                var type              = contributorObject.get("type").getAsString();
                this.listOfContributor.put(UUID, new ImmutablePair<>(name, type));
                DiaboloLib.LOGGER.debug(new FormattedMessage("Contributor found: %s/%s/%s", UUID, name, type));
            });
        } catch (IOException e) {
            DiaboloLib.LOGGER.error(new FormattedMessage("Error during ContributorEvent event execution: %s", e.getMessage()));
        }
    }

    /**
     * Function use for spawn the firework
     *
     * @param player PlayerEntity use of get player position inside the world
     * @param world  Current world
     * @param star   The firework star element
     */
    private void generateFirework(@NotNull Player player, @NotNull Level world, CompoundTag star, int flight) {
        var firework         = new ItemStack(Items.FIREWORK_ROCKET);
        var rocket           = new CompoundTag();
        var fireworkCompound = firework.getOrCreateTag();

        rocket.putInt("Flight", flight);
        rocket.put("Explosions", Arrays.stream(new CompoundTag[]{ star }).collect(Collectors.toCollection(ListTag::new)));

        fireworkCompound.put("Fireworks", rocket);

        DiaboloLib.LOGGER.debug(new FormattedMessage("Spawning [%s] Firework.", player.getName().getString()));

        world.addFreshEntity(new FireworkRocketEntity(world, player.xOld, player.yOld, player.zOld, firework));
    }

    @Contract(pure = true)
    private int getColor(@NotNull String contributionType) {
        return switch (contributionType) {
            case "code" -> 6719955; // light blue
            case "test" -> 4312372; // lime
            default -> 15790320;    // white
        };
    }

    private @NotNull CompoundTag getAuthorFirework(@NotNull String UUID) {
        var star = new CompoundTag();
        switch (UUID) {
            case ALASDIABLO_UUID -> {
                star.putIntArray("Colors", Collections.singletonList(15790320));
                star.putIntArray("FadeColors", Collections.singletonList(11743532));
                star.putBoolean("Flicker", true);
                star.putBoolean("Trail", true);
                star.putInt("Type", 1);
            }
            case SAFYRUS_UUID -> {
                star.putIntArray("Colors", Lists.newArrayList(6719955, 15790320));
                star.putIntArray("FadeColors", Lists.newArrayList(2437522, 11250603));
                star.putBoolean("Flicker", true);
                star.putBoolean("Trail", true);
                star.putInt("Type", 2);
            }
            case SMARTZI_UUID -> {
                star.putIntArray("Colors", Collections.singletonList(14188952));
                star.putIntArray("FadeColors", Collections.singletonList(11743532));
                star.putBoolean("Trail", true);
                star.putInt("Type", 1);
            }
        }
        return star;
    }

    private @NotNull CompoundTag getContributorFirework(String UUID) {
        var contributionType = this.listOfContributor.get(UUID).value();
        var star             = new CompoundTag();
        star.putIntArray("Colors", Collections.singletonList(this.getColor(contributionType)));
        star.putInt("Type", 4);
        return star;
    }

    private @NotNull ImmutablePair<Boolean, CompoundTag> getStarFirework(@NotNull String UUID) {
        return switch (UUID) {
            case ALASDIABLO_UUID, SAFYRUS_UUID, SMARTZI_UUID -> ImmutablePair.of(true, this.getAuthorFirework(UUID));
            default -> ImmutablePair.of(false, this.getContributorFirework(UUID));
        };
    }

    /**
     * Function use to handle the event
     *
     * @param event Instance of the event
     */
    @Override
    public void onEvent(@NotNull PlayerEvent.PlayerLoggedInEvent event) {
        var player = event.getEntity();
        var world  = player.level();
        var UUID   = player.getStringUUID();
        if (DiaboloLibConfig.CONTRIBUTOR_FIREWORK.canContributorFirework()) {
            if (this.listOfContributor.containsKey(UUID)) {
                var star = this.getStarFirework(UUID);
                this.generateFirework(player, world, star.value(), star.key() ? 2 : 1);
            }
        }
    }
}
