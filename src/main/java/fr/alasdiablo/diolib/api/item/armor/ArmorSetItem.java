package fr.alasdiablo.diolib.api.item.armor;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArmorSetItem {
    private final RegistryObject<Item> helmetRegistryObject;
    private final RegistryObject<Item> chestplateRegistryObject;
    private final RegistryObject<Item> leggingsRegistryObject;
    private final RegistryObject<Item> bootsRegistryObject;

    public ArmorSetItem(
            @NotNull ArmorItemRegistryName registryName, @NotNull ArmorMaterial material, Item.@NotNull Properties properties,
            @NotNull DeferredRegister<Item> register
    ) {
        this.helmetRegistryObject     = register.register(
                registryName.getHelmetName(),
                () -> new ArmorItem(material, ArmorItem.Type.HELMET, properties)
        );
        this.chestplateRegistryObject = register.register(
                registryName.getChestplateName(),
                () -> new ArmorItem(material, ArmorItem.Type.CHESTPLATE, properties)
        );
        this.leggingsRegistryObject   = register.register(
                registryName.getLeggingsName(),
                () -> new ArmorItem(material, ArmorItem.Type.LEGGINGS, properties)
        );
        this.bootsRegistryObject      = register.register(
                registryName.getBootsName(),
                () -> new ArmorItem(material, ArmorItem.Type.BOOTS, properties)
        );
    }

    public ArmorSetItem(
            @NotNull Map<ArmorItem.Type, TriFunction<ArmorMaterial, ArmorItem.Type, Item.Properties, ArmorItem>> armorConstructor,
            @NotNull ArmorItemRegistryName registryName, @NotNull ArmorMaterial material, Item.@NotNull Properties properties,
            @NotNull DeferredRegister<Item> register
    ) {
        var map = new HashMap<>(armorConstructor);

        if (!map.containsKey(ArmorItem.Type.HELMET)) {
            map.put(ArmorItem.Type.HELMET, ArmorItem::new);
        }
        if (!map.containsKey(ArmorItem.Type.CHESTPLATE)) {
            map.put(ArmorItem.Type.CHESTPLATE, ArmorItem::new);
        }
        if (!map.containsKey(ArmorItem.Type.LEGGINGS)) {
            map.put(ArmorItem.Type.LEGGINGS, ArmorItem::new);
        }
        if (!map.containsKey(ArmorItem.Type.BOOTS)) {
            map.put(ArmorItem.Type.BOOTS, ArmorItem::new);
        }

        this.helmetRegistryObject     = register.register(
                registryName.getHelmetName(),
                () -> map.get(ArmorItem.Type.HELMET).apply(material, ArmorItem.Type.HELMET, properties)
        );
        this.chestplateRegistryObject = register.register(
                registryName.getChestplateName(),
                () -> map.get(ArmorItem.Type.CHESTPLATE).apply(material, ArmorItem.Type.CHESTPLATE, properties)
        );
        this.leggingsRegistryObject   = register.register(
                registryName.getLeggingsName(),
                () -> map.get(ArmorItem.Type.LEGGINGS).apply(material, ArmorItem.Type.LEGGINGS, properties)
        );
        this.bootsRegistryObject      = register.register(
                registryName.getBootsName(),
                () -> map.get(ArmorItem.Type.BOOTS).apply(material, ArmorItem.Type.BOOTS, properties)
        );
    }

    public @NotNull List<Item> getAll() {
        return List.of(
                getHelmet(),
                getChestplate(),
                getLeggings(),
                getBoots()
        );
    }

    public @NotNull Item getHelmet() {
        return helmetRegistryObject.get();
    }

    public @NotNull Item getChestplate() {
        return chestplateRegistryObject.get();
    }

    public @NotNull Item getLeggings() {
        return leggingsRegistryObject.get();
    }

    public @NotNull Item getBoots() {
        return bootsRegistryObject.get();
    }
}