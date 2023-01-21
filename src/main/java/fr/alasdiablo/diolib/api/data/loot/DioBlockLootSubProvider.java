package fr.alasdiablo.diolib.api.data.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

public abstract class DioBlockLootSubProvider extends BlockLootSubProvider {
    protected DioBlockLootSubProvider(Set<Item> pExplosionResistant, FeatureFlagSet pEnabledFeatures) {
        super(pExplosionResistant, pEnabledFeatures);
    }

    public void generate(@NotNull BiConsumer<ResourceLocation, LootTable.Builder> builder) {
        this.generate();
        Set<ResourceLocation> set = new HashSet<>();

        for(Block block : getKnownBlocks()) {
            ResourceLocation resourcelocation = block.getLootTable();
            if (resourcelocation != BuiltInLootTables.EMPTY && set.add(resourcelocation)) {
                LootTable.Builder lootTableBuilder = this.map.remove(resourcelocation);
                if (lootTableBuilder != null) {
                    builder.accept(resourcelocation, lootTableBuilder);
                }
            }
        }

        if (!this.map.isEmpty()) {
            throw new IllegalStateException("Created block loot tables for non-blocks: " + this.map.keySet());
        }
    }
}
