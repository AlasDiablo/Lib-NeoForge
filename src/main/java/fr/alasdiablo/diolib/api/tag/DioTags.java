package fr.alasdiablo.diolib.api.tag;

import fr.alasdiablo.diolib.DiaboloLib;
import fr.alasdiablo.diolib.api.util.ResourceLocations;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

public class DioTags {
    public static final TagKey<Item> BOOTS_WALK_ON_POWDER_SNOW = tag("boots_walk_on_powder_snow");

    public static void init() {}

    private static @NotNull TagKey<Item> tag(String name) {
        return ItemTags.create(ResourceLocations.of(DiaboloLib.MOD_ID, name));
    }
}
