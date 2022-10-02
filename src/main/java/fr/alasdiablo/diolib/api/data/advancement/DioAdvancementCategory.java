package fr.alasdiablo.diolib.api.data.advancement;

import net.minecraft.advancements.Advancement;

import java.util.function.Consumer;

/**
 * Abstract class used to create Advancement Category
 */
public abstract class DioAdvancementCategory implements Consumer<Consumer<Advancement>> {
    @Override
    public abstract void accept(Consumer<Advancement> advancementBuilder);
}
