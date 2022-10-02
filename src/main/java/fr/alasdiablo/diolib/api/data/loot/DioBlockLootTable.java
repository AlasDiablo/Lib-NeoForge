package fr.alasdiablo.diolib.api.data.loot;

import com.google.common.collect.Sets;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;
import java.util.function.BiConsumer;

@SuppressWarnings("unused")
@ParametersAreNonnullByDefault
public abstract class DioBlockLootTable extends BlockLoot {
    @Override
    protected abstract void addTables();

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> lootTables) {
        this.addTables();
        Set<ResourceLocation> set = Sets.newHashSet();

        for (Block block: getKnownBlocks()) {
            ResourceLocation resourcelocation = block.getLootTable();
            if (resourcelocation != BuiltInLootTables.EMPTY && set.add(resourcelocation)) {
                LootTable.Builder lootTableBuilder = this.map.remove(resourcelocation);
                if (lootTableBuilder != null) {
                    lootTables.accept(resourcelocation, lootTableBuilder);
                }
            }
        }

        if (!this.map.isEmpty()) {
            throw new IllegalStateException("Created block loot tables for non-blocks: " + this.map.keySet());
        }
    }
}
