package fr.alasdiablo.diolib.data;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.util.ResourceLocation;

import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public abstract class DioBlockLootTables extends BlockLootTables {
    private final Map<ResourceLocation, LootTable.Builder> lootTables = Maps.newHashMap();

    @Override
    protected abstract void addTables();

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> lootTable) {
        this.addTables();
        Set<ResourceLocation> set = Sets.newHashSet();

        for (Block block : getKnownBlocks()) {
            ResourceLocation resourcelocation = block.getLootTable();
            if (resourcelocation != LootTables.EMPTY && set.add(resourcelocation)) {
                LootTable.Builder lootTableBuilder = this.lootTables.remove(resourcelocation);
                if (lootTableBuilder != null) {
                    lootTable.accept(resourcelocation, lootTableBuilder);
                }
            }
        }

        if (!this.lootTables.isEmpty()) {
            throw new IllegalStateException("Created block loot tables for non-blocks: " + this.lootTables.keySet());
        }
    }

    @Override
    protected void registerLootTable(Block blockIn, LootTable.Builder table) {
        this.lootTables.put(blockIn.getLootTable(), table);
    }
}
