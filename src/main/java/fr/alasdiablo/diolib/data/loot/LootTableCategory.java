package fr.alasdiablo.diolib.data.loot;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public abstract class LootTableCategory implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {
    @Override
    public abstract void accept(BiConsumer<ResourceLocation, LootTable.Builder> resourceLocationBuilderBiConsumer);
}
