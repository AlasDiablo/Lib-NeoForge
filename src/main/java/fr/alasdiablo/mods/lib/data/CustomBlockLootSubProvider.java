package fr.alasdiablo.mods.lib.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * An abstract class that extends BlockLootSubProvider to provide custom loot table generation functionality for blocks.
 * This class manages the creation and handling of loot tables for Minecraft blocks with additional customisation options.
 */
@SuppressWarnings("unused")
public abstract class CustomBlockLootSubProvider extends BlockLootSubProvider {

    /**
     * Constructs a CustomBlockLootSubProvider with basic configuration parameters.
     *
     * @param pExplosionResistant Set of items that are resistant to explosions
     * @param pEnabledFeatures    Set of enabled feature flags that determine block availability
     * @param pRegistries         Provider for looking up game objects
     */
    protected CustomBlockLootSubProvider(Set<Item> pExplosionResistant, FeatureFlagSet pEnabledFeatures, HolderLookup.Provider pRegistries) {
        super(pExplosionResistant, pEnabledFeatures, pRegistries);
    }

    /**
     * Constructs a CustomBlockLootSubProvider with advanced configuration including pre-defined loot tables.
     *
     * @param pExplosionResistant Set of items that are resistant to explosions
     * @param pEnabledFeatures    Set of enabled feature flags that determine block availability
     * @param pMap                Pre-defined mapping of loot tables to their builders
     * @param pRegistries         Provider for looking up game objects
     */
    protected CustomBlockLootSubProvider(
            Set<Item> pExplosionResistant, FeatureFlagSet pEnabledFeatures, Map<ResourceKey<LootTable>, LootTable.Builder> pMap,
            HolderLookup.Provider pRegistries
    ) {
        super(pExplosionResistant, pEnabledFeatures, pMap, pRegistries);
    }

    /**
     * Generates and processes loot tables for all known blocks.
     * This method ensures that only enabled blocks receive loot tables and handles any conflicts or missing entries.
     *
     * @param builder Consumer that accepts the generated loot table entries
     * @throws IllegalStateException if loot tables are created for non-block entries
     */
    @Override
    public void generate(@NotNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> builder) {
        this.generate();

        Set<ResourceKey<LootTable>> set = new HashSet<>();

        for (Block block : getKnownBlocks()) {
            if (block.isEnabled(this.enabledFeatures)) {
                Optional<ResourceKey<LootTable>> optionalResourceKey = block.getLootTable();

                if (optionalResourceKey.isPresent()) {
                    ResourceKey<LootTable> resourceKey = optionalResourceKey.get();
                    if (set.add(resourceKey)) {
                        LootTable.Builder lootTableBuilder = this.map.remove(resourceKey);
                        if (lootTableBuilder != null) {
                            builder.accept(resourceKey, lootTableBuilder);
                        }
                    }
                }
            }
        }

        if (!this.map.isEmpty()) {
            throw new IllegalStateException("Created block loot tables for non-blocks: " + this.map.keySet());
        }
    }
}