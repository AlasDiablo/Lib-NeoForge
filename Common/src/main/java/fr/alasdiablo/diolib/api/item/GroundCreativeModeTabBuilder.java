package fr.alasdiablo.diolib.api.item;

import fr.alasdiablo.diolib.platform.Services;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class GroundCreativeModeTabBuilder {
    public static CreativeModeTab createCreativeTab(ItemStack itemStack, String modid, String label) {
        return Services.PLATFORM.createTab(itemStack, modid, label);
    }
}
