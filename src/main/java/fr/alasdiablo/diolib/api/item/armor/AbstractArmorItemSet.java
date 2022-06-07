package fr.alasdiablo.diolib.api.item.armor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

/**
 * Abstract class use by ArmorItemSet and DeferredArmorItemSet
 *
 * @see fr.alasdiablo.diolib.api.item.armor.ArmorItemSet
 * @see fr.alasdiablo.diolib.api.item.armor.DeferredArmorItemSet
 */
@SuppressWarnings("unused")
public abstract class AbstractArmorItemSet {
    /**
     * Default helmet provider
     *
     * @see ArmorItemProvider
     */
    public static final ArmorItemProvider DEFAULT_HELMET;
    /**
     * Default chestplate provider
     *
     * @see ArmorItemProvider
     */
    public static final ArmorItemProvider DEFAULT_CHESTPLATE;
    /**
     * Default leggings provider
     *
     * @see ArmorItemProvider
     */
    public static final ArmorItemProvider DEFAULT_LEGGINGS;
    /**
     * Default boots provider
     *
     * @see ArmorItemProvider
     */
    public static final ArmorItemProvider DEFAULT_BOOTS;

    static {
        DEFAULT_HELMET     = (material, properties) -> new ArmorItem(material, EquipmentSlot.HEAD, properties);
        DEFAULT_CHESTPLATE = (material, properties) -> new ArmorItem(material, EquipmentSlot.CHEST, properties);
        DEFAULT_LEGGINGS   = (material, properties) -> new ArmorItem(material, EquipmentSlot.LEGS, properties);
        DEFAULT_BOOTS      = (material, properties) -> new ArmorItem(material, EquipmentSlot.FEET, properties);
    }

    protected final ArmorItemRegistryName registryName;
    protected final Item                  helmet;
    protected final Item                  chestplate;
    protected final Item                  leggings;
    protected final Item                  boots;

    /**
     * Default constructor to create simple armor set
     *
     * @param registryName Registry name of each armor piece
     * @param material     Material use by this armor set
     * @param properties   Properties of the armor set items
     */
    public AbstractArmorItemSet(@NotNull ArmorItemRegistryName registryName, @NotNull ArmorMaterial material, @NotNull Item.Properties properties) {
        this(registryName, material, properties, DEFAULT_HELMET, DEFAULT_CHESTPLATE, DEFAULT_LEGGINGS, DEFAULT_BOOTS);
    }

    /**
     * Customisable constructor used to create advanced armor set
     *
     * @param registryName Registry name of each armor piece
     * @param material     Material use by this armor set
     * @param properties   Properties of the armor set items
     * @param helmet       Item provider use to create the helmet item
     * @param chestplate   Item provider use to create the chestplate item
     * @param leggings     Item provider use to create the leggings item
     * @param boots        Item provider use to create the boots item
     */
    public AbstractArmorItemSet(
            @NotNull ArmorItemRegistryName registryName, @NotNull ArmorMaterial material, @NotNull Item.Properties properties,
            @NotNull ArmorItemProvider helmet, @NotNull ArmorItemProvider chestplate, @NotNull ArmorItemProvider leggings, @NotNull ArmorItemProvider boots
    ) {
        this.helmet     = helmet.apply(material, properties);
        this.chestplate = chestplate.apply(material, properties);
        this.leggings   = leggings.apply(material, properties);
        this.boots      = boots.apply(material, properties);

        this.registryName = registryName;
    }

    /**
     * @return Finished helmet item
     */
    public @NotNull Item getHelmet() {
        return this.helmet;
    }

    /**
     * @return Finished chestplate item
     */
    public @NotNull Item getChestplate() {
        return this.chestplate;
    }

    /**
     * @return Finished leggings item
     */
    public @NotNull Item getLeggings() {
        return this.leggings;
    }

    /**
     * @return Finished boots item
     */
    public @NotNull Item getBoots() {
        return this.boots;
    }
}
