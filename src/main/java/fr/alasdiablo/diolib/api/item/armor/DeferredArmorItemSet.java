package fr.alasdiablo.diolib.api.item.armor;

import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

/**
 * Create a ArmorItemSet who need to be registered via DeferredRegister<Item>
 */
@SuppressWarnings("unused")
public class DeferredArmorItemSet extends AbstractArmorItemSet {
    private RegistryObject<Item> helmetRegistryObject;
    private RegistryObject<Item> chestplateRegistryObject;
    private RegistryObject<Item> leggingsRegistryObject;
    private RegistryObject<Item> bootsRegistryObject;

    /**
     * Default constructor to create simple armor set
     * @param registryName Registry name of each armor piece
     * @param material Material use by this armor set
     * @param properties Properties of the armor set items
     */
    public DeferredArmorItemSet(
            @NotNull ArmorItemRegistryName registryName,
            @NotNull ArmorMaterial material,
            @NotNull Item.Properties properties
    ) {
        super(registryName, material, properties);
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
    public DeferredArmorItemSet(
            @NotNull ArmorItemRegistryName registryName,
            @NotNull ArmorMaterial material,
            @NotNull Item.Properties properties,
            @NotNull ArmorItemProvider helmet,
            @NotNull ArmorItemProvider chestplate,
            @NotNull ArmorItemProvider leggings,
            @NotNull ArmorItemProvider boots
    ) {
        super(registryName, material, properties, helmet, chestplate, leggings, boots);
    }

    public DeferredArmorItemSet register(@NotNull DeferredRegister<Item> register) {
        this.helmetRegistryObject = register.register(registryName.getHelmetName(), () -> this.helmet);
        this.chestplateRegistryObject = register.register(registryName.getChestplateName(), () -> this.chestplate);
        this.leggingsRegistryObject = register.register(registryName.getLeggingsName(), () -> this.leggings);
        this.bootsRegistryObject = register.register(registryName.getBootsName(), () -> this.boots);
        return this;
    }

    public RegistryObject<Item> getHelmetRegistryObject() {
        return helmetRegistryObject;
    }

    public RegistryObject<Item> getChestplateRegistryObject() {
        return chestplateRegistryObject;
    }

    public RegistryObject<Item> getLeggingsRegistryObject() {
        return leggingsRegistryObject;
    }

    public RegistryObject<Item> getBootsRegistryObject() {
        return bootsRegistryObject;
    }
}
