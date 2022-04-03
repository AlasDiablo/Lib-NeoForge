package fr.alasdiablo.diolib.tag;

import fr.alasdiablo.diolib.DiaboloLib;
import fr.alasdiablo.diolib.registries.RegistryHelper;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class DioTags {
    public static final TagKey<Item> BOOTS_WALK_ON_POWDER_SNOW = tag("boots_walk_on_powder_snow");

    public static void init() {}

    private static TagKey<Item> tag(String name) {
        return ItemTags.create(RegistryHelper.rl(DiaboloLib.MOD_ID, name));
    }
}
