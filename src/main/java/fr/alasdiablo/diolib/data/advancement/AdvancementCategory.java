package fr.alasdiablo.diolib.data.advancement;

import net.minecraft.advancements.Advancement;

import java.util.function.Consumer;

public abstract class AdvancementCategory implements Consumer<Consumer<Advancement>> {
    @Override
    public abstract void accept(Consumer<Advancement> advancementBuilder);
}
