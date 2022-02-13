package fr.alasdiablo.diolib.item;

import fr.alasdiablo.diolib.registries.RegistryHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

/**
 * @since 4.3.24
 * @author lIotaMiu
 * Item set provider use to create armor item set
 */
@SuppressWarnings("unused")
public class ArmorItemSet {
    /**
     * Default helmet provider
     * @see ArmorItemProvider
     */
    public static final ArmorItemProvider DEFAULT_HELMET;
    /**
     * Default chestplate provider
     * @see ArmorItemProvider
     */
    public static final ArmorItemProvider DEFAULT_CHESTPLATE;
    /**
     * Default leggings provider
     * @see ArmorItemProvider
     */
    public static final ArmorItemProvider DEFAULT_LEGGINGS;
    /**
     * Default boots provider
     * @see ArmorItemProvider
     */
    public static final ArmorItemProvider DEFAULT_BOOTS;

    static {
        DEFAULT_HELMET     = (material, properties) -> new ArmorItem(material, EquipmentSlot.HEAD, properties);
        DEFAULT_CHESTPLATE = (material, properties) -> new ArmorItem(material, EquipmentSlot.CHEST, properties);
        DEFAULT_LEGGINGS   = (material, properties) -> new ArmorItem(material, EquipmentSlot.LEGS, properties);
        DEFAULT_BOOTS      = (material, properties) -> new ArmorItem(material, EquipmentSlot.FEET, properties);
    }

    private final Item helmet;
    private final Item chestplate;
    private final Item leggings;
    private final Item boots;

    /**
     * Default constructor to create simple armor set
     * @param registryName Registry name of each armor piece
     * @param material Material use by this armor set
     * @param properties Properties of the armor set items
     */
    public ArmorItemSet(@NotNull ArmorItemRegistryName registryName, @NotNull ArmorMaterial material, @NotNull Item.Properties properties) {
        this(registryName, material, properties, DEFAULT_HELMET, DEFAULT_CHESTPLATE, DEFAULT_LEGGINGS, DEFAULT_BOOTS);
    }

    /**
     * Customisable constructor use to create advanced armor set
     * @param registryName Registry name of each armor piece
     * @param material Material use by this armor set
     * @param properties Properties of the armor set items
     * @param helmet Item provider use to create the helmet item
     * @param chestplate Item provider use to create the chestplate item
     * @param leggings Item provider use to create the leggings item
     * @param boots Item provider use to create the boots item
     */
    public ArmorItemSet(
            @NotNull ArmorItemRegistryName registryName, @NotNull ArmorMaterial material, @NotNull Item.Properties properties,
            @NotNull ArmorItemProvider helmet, @NotNull ArmorItemProvider chestplate, @NotNull ArmorItemProvider leggings, @NotNull ArmorItemProvider boots
    ) {
        this.helmet     = helmet.apply(material, properties);
        this.chestplate = chestplate.apply(material, properties);
        this.leggings   = leggings.apply(material, properties);
        this.boots      = boots.apply(material, properties);

        this.helmet.setRegistryName(registryName.getHelmet());
        this.chestplate.setRegistryName(registryName.getChestplate());
        this.leggings.setRegistryName(registryName.getLeggings());
        this.boots.setRegistryName(registryName.getBoots());
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

    /**
     * Interface use as a wrapper to a BiFunction
     * Take an ArmorMaterial as the first argument to the function
     * Take an Item.Properties as the second argument to the function
     * Return an Item as the result of the function
     */
    public interface ArmorItemProvider extends BiFunction<ArmorMaterial, Item.Properties, Item> {}

    /**
     * This record is use as a registry name provider use to create each registry name of an armor set
     * @param modId ID of the mod use to create the domaine of the ResourceLocation
     * @param name Actual armor name use to create the complete registry name
     */
    public record ArmorItemRegistryName(String modId, String name) {

        /**
         * Create the registry name with the given arguments
         * @return The registry name of the helmet
         */
        @Contract(" -> new")
        public @NotNull ResourceLocation getHelmet() {
            return RegistryHelper.rl(this.modId, this.name + "_helmet");
        }

        /**
         * Create the registry name with the given arguments
         * @return The registry name of the chestplate
         */
        @Contract(" -> new")
        public @NotNull ResourceLocation getChestplate() {
            return RegistryHelper.rl(this.modId, this.name + "_chestplate");
        }

        /**
         * Create the registry name with the given arguments
         * @return The registry name of the leggings
         */
        @Contract(" -> new")
        public @NotNull ResourceLocation getLeggings() {
            return RegistryHelper.rl(this.modId, this.name + "_leggings");
        }

        /**
         * Create the registry name with the given arguments
         * @return The registry name of the boots
         */
        @Contract(" -> new")
        public @NotNull ResourceLocation getBoots() {
            return RegistryHelper.rl(this.modId, this.name + "_boots");
        }
    }
}
