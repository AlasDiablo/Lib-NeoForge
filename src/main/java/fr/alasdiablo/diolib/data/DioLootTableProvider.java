package fr.alasdiablo.diolib.data;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.datafixers.util.Pair;
import fr.alasdiablo.diolib.DiaboloLib;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.loot.*;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DioLootTableProvider extends LootTableProvider {
    private final DataGenerator dataGenerator;
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    protected final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> lootTableList;
    private final LootParameterSet lootParameterSet;
    private final String name;


    public DioLootTableProvider(DataGenerator dataGeneratorIn, List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> lootTableListIn, LootParameterSet lootParameterSetIn, String nameIn) {
        super(dataGeneratorIn);
        this.dataGenerator = dataGeneratorIn;
        this.lootTableList = lootTableListIn;
        this.lootParameterSet = lootParameterSetIn;
        this.name = nameIn;
    }

    @Override
    public void act(DirectoryCache cache) {
        final Path path = this.dataGenerator.getOutputFolder();
        final Map<ResourceLocation, LootTable> map = Maps.newHashMap();

        this.getTables().forEach((lootParameterSetPair) -> lootParameterSetPair.getFirst().get().accept((resourceLocation, builder) -> {
            if (map.put(resourceLocation, builder.setParameterSet(lootParameterSetPair.getSecond()).build()) != null) {
                throw new IllegalStateException("Duplicate loot table " + resourceLocation);
            }
        }));

        ValidationTracker validationtracker = new ValidationTracker(this.lootParameterSet, (resourceLocation) -> null, map::get);
        this.validate(map, validationtracker);
        Multimap<String, String> multimap = validationtracker.getProblems();

        if (!multimap.isEmpty()) {
            multimap.forEach((name, problem) -> DiaboloLib.logger.warn("Found validation problem in " + name + ": " + problem));
            throw new IllegalStateException("Failed to validate loot tables, see logs");
        } else {
            map.forEach((resourceLocation, lootTable) -> {
                Path path1 = getPath(path, resourceLocation);

                try {
                    IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path1);
                } catch (IOException ioexception) {
                    DiaboloLib.logger.error("Couldn't save loot table {}", path1, ioexception);
                }

            });
        }
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
        map.forEach((resourceLocation, lootTable) -> LootTableManager.validateLootTable(validationtracker, resourceLocation, lootTable));
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return this.lootTableList;
    }

    private static Path getPath(Path pathIn, ResourceLocation id) {
        return pathIn.resolve("data/" + id.getNamespace() + "/loot_tables/" + id.getPath() + ".json");
    }

    @Override
    public String getName() {
        return this.name + " - LootTables";
    }
}
