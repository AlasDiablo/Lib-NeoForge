package fr.alasdiablo.diolib.tag;

import fr.alasdiablo.diolib.DiaboloLib;
import fr.alasdiablo.diolib.registries.RegistryHelper;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

public class DioTags {
    public static final Tags.IOptionalNamedTag<Item> BOOTS_WALK_ON_POWDER_SNOW = tag("boots_walk_on_powder_snow");

    public static void init() {}

    private static @NotNull Tags.IOptionalNamedTag<Item> tag(String name) {
        return ItemTags.createOptional(RegistryHelper.rl(DiaboloLib.MOD_ID, name));
    }
}
