package fr.alasdiablo.mods.lib.api.data.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.BiConsumer;

@SuppressWarnings("unused")
public abstract class DioBlockLootSubProvider extends BlockLootSubProvider {

    protected DioBlockLootSubProvider(Set<Item> pExplosionResistant, FeatureFlagSet pEnabledFeatures, HolderLookup.Provider pRegistries) {
        super(pExplosionResistant, pEnabledFeatures, pRegistries);
    }

    protected DioBlockLootSubProvider(
            Set<Item> pExplosionResistant, FeatureFlagSet pEnabledFeatures, Map<ResourceKey<LootTable>, LootTable.Builder> pMap,
            HolderLookup.Provider pRegistries
    ) {
        super(pExplosionResistant, pEnabledFeatures, pMap, pRegistries);
    }

    @Override
    public void generate(@NotNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> builder) {
        this.generate();

        Set<ResourceKey<LootTable>> set  = new HashSet<>();

        for (Block block: this.getKnownBlocks()) {
            if (block.isEnabled(this.enabledFeatures)) {
                ResourceKey<LootTable> resourceKey = block.getLootTable();
                if (resourceKey != BuiltInLootTables.EMPTY && set.add(resourceKey)) {
                    LootTable.Builder lootTableBuilder = this.map.remove(resourceKey);
                    if (lootTableBuilder != null) {
                        builder.accept(resourceKey, lootTableBuilder);
                    }
                }
            }
        }

        if (!this.map.isEmpty()) {
            throw new IllegalStateException("Created block loot tables for non-blocks: " + this.map.keySet());
        }
    }
}
