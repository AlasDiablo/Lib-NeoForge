package fr.alasdiablo.diolib.api.item.armor;

import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

import java.util.function.BiFunction;

/**
 * Interface use as a wrapper to a BiFunction
 * Take an ArmorMaterial as the first argument to the function
 * Take an Item.Properties as the second argument to the function
 * Return an Item as the result of the function
 */
public interface ArmorItemProvider extends BiFunction<ArmorMaterial, Item.Properties, Item> {
}
