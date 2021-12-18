package fr.alasdiablo.diolib.data.loot;

import net.minecraft.data.loot.BlockLoot;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("unused")
@ParametersAreNonnullByDefault
public abstract class BlockLootTable extends BlockLoot {
    @Override
    protected abstract void addTables();
}
