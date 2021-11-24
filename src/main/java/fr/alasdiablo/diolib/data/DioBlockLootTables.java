package fr.alasdiablo.diolib.data;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

@SuppressWarnings("unused")
@ParametersAreNonnullByDefault
public abstract class DioBlockLootTables extends BlockLoot {
    private final Map<ResourceLocation, LootTable.Builder> lootTables = Maps.newHashMap();

    @Override
    protected abstract void addTables();

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> lootTable) {
        this.addTables();
        Set<ResourceLocation> set = Sets.newHashSet();

        for (Block block: getKnownBlocks()) {
            ResourceLocation resourcelocation = block.getLootTable();
            if (resourcelocation != BuiltInLootTables.EMPTY && set.add(resourcelocation)) {
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
    protected void add(Block blockIn, LootTable.Builder table) {
        this.lootTables.put(blockIn.getLootTable(), table);
    }
}
