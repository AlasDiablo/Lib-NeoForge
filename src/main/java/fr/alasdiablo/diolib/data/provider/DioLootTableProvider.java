package fr.alasdiablo.diolib.data.provider;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Loot Table Provider used to create loot table during gather data event
 */
@SuppressWarnings("unused")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public abstract class DioLootTableProvider extends LootTableProvider {

    /**
     * Current mod id
     */
    private final String modId;

    /**
     * List of loot table
     */
    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> lootTables;

    /**
     * Default contrutor
     *
     * @param generator Data generator
     * @param modId     Mod id
     */
    public DioLootTableProvider(DataGenerator generator, String modId) {
        super(generator);
        this.modId      = modId;
        this.lootTables = Lists.newArrayList();
    }

    /**
     * Function used to add a loot table
     *
     * @param lootTable Add the loot table into the list of loot table
     */
    public void addLootTable(Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet> lootTable) {
        this.lootTables.add(lootTable);
    }

    /**
     * Function used to add loot table before getTables()
     */
    public abstract void registerAdvancements();

    /**
     * Validate the build loot table
     *
     * @param map               Map of build loot table
     * @param validationTracker Validation Tracker
     */
    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationTracker) {
        map.forEach((resourceLocation, lootTable) -> LootTables.validate(validationTracker, resourceLocation, lootTable));
    }

    /**
     * Call registerAdvancements() and return the loot table list
     *
     * @return Loot table list
     */
    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        this.registerAdvancements();
        return this.lootTables;
    }

    @Override
    public String getName() {
        return "LootTables" + this.modId;
    }
}
