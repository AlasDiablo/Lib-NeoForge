package fr.alasdiablo.diolib.platform.services;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public interface IPlatformHelper {
    String getConfigPath();
    CreativeModeTab createTab(ItemStack itemStack, String modid, String label);
}
