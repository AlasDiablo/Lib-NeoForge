package fr.alasdiablo.diolib.util;

import net.minecraft.resources.ResourceLocation;

public class Utils {

    public static ResourceLocation rl(String modid, String path) {
        return new ResourceLocation(modid, path);
    }
}
