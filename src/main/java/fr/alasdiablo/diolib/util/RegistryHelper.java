package fr.alasdiablo.diolib.util;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.Objects;

public class RegistryHelper {

    /**
     * Utils use to add a prefix to a registryName
     *
     * @param registryName The RegistryName which receives the prefix
     * @param prefix       The prefix that is added to the RegistryName
     * @return Return the addition of the <i>registryName</i> and the <i>prefix</i>
     */
    public static ResourceLocation setPrefixOnRegistryName(@Nullable ResourceLocation registryName, String prefix) {
        return registryName == null || prefix == null ? null : new ResourceLocation(registryName.getNamespace(), String.format("%s_%s", prefix, registryName.getPath()));
    }

    /**
     * Utils use to clean registry access on Block registry
     *
     * @param registry Instance of the block registry
     * @param blocks   Array of block need to be register
     */
    public static void registerBlock(IForgeRegistry<Block> registry, Block... blocks) {
        for (Block block : blocks) {
            register(registry, block);
        }
    }

    /**
     * Utils use to clean registry access on BlockItem registry
     *
     * @param registry   Instance of the item registry
     * @param properties Properties of the BlockItem
     * @param blocks     Array of block need to be register as BlockItem
     */
    public static void registerBlockItem(IForgeRegistry<Item> registry, Item.Properties properties, Block... blocks) {
        for (Block block : blocks) {
            register(registry, new BlockItem(block, properties).setRegistryName(Objects.requireNonNull(block.getRegistryName())));
        }
    }

    /**
     * Utils use to clean registry access on Item registry
     *
     * @param registry Instance of the item registry
     * @param items    Array of item need to be register
     */
    public static void registerItem(IForgeRegistry<Item> registry, Item... items) {
        for (Item item : items) {
            register(registry, item);
        }
    }

    /**
     * Function use by &#60;registerItem/registerBlockItem/registerBlock&#62; for access and register an &#60;Item/Block&#62;
     *
     * @param registry Instance of &#60;Item/Block&#62; registry
     * @param object   &#60;Item/Block&#62; need to be register
     * @param <T>      &#60;Item/Block&#62;
     */
    private static <T extends IForgeRegistryEntry<T>> void register(IForgeRegistry<T> registry, T object) {
        registry.register(object);
    }
}
