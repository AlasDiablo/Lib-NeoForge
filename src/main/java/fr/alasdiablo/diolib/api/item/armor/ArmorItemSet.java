package fr.alasdiablo.diolib.api.item.armor;

import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

/**
 * Create a ArmorItemSet who need item have the registryName set and need to be registered individually
 */
@SuppressWarnings("unused")
public class ArmorItemSet extends AbstractArmorItemSet {

    /**
     * Default constructor to create simple armor set
     * @param registryName Registry name of each armor piece
     * @param material Material use by this armor set
     * @param properties Properties of the armor set items
     */
    public ArmorItemSet(
            @NotNull ArmorItemRegistryName registryName,
            @NotNull ArmorMaterial material,
            @NotNull Item.Properties properties
    ) {
        super(registryName, material, properties);
        this.helmet.setRegistryName(registryName.getHelmet());
        this.chestplate.setRegistryName(registryName.getChestplate());
        this.leggings.setRegistryName(registryName.getLeggings());
        this.boots.setRegistryName(registryName.getBoots());
    }

    /**
     * Customisable constructor used to create advanced armor set
     * @param registryName Registry name of each armor piece
     * @param material Material use by this armor set
     * @param properties Properties of the armor set items
     * @param helmet Item provider use to create the helmet item
     * @param chestplate Item provider use to create the chestplate item
     * @param leggings Item provider use to create the leggings item
     * @param boots Item provider use to create the boots item
     */
    public ArmorItemSet(
            @NotNull ArmorItemRegistryName registryName,
            @NotNull ArmorMaterial material,
            @NotNull Item.Properties properties,
            @NotNull ArmorItemProvider helmet,
            @NotNull ArmorItemProvider chestplate,
            @NotNull ArmorItemProvider leggings,
            @NotNull ArmorItemProvider boots
    ) {
        super(registryName, material, properties, helmet, chestplate, leggings, boots);
        this.helmet.setRegistryName(registryName.getHelmet());
        this.chestplate.setRegistryName(registryName.getChestplate());
        this.leggings.setRegistryName(registryName.getLeggings());
        this.boots.setRegistryName(registryName.getBoots());
    }
}