package fr.alasdiablo.diolib.data;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.datafixers.util.Pair;
import fr.alasdiablo.diolib.DiaboloLib;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("unused")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class DioLootTableProvider extends LootTableProvider {
    private static final Gson                                                                                                 GSON
            = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    protected final      List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> lootTableList;
    private final        DataGenerator                                                                                        dataGenerator;
    private final        LootContextParamSet                                                                                  lootParameterSet;
    private final        String                                                                                               name;


    public DioLootTableProvider(
            DataGenerator dataGeneratorIn, List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> lootTableListIn,
            LootContextParamSet lootParameterSetIn, String nameIn
    ) {
        super(dataGeneratorIn);
        this.dataGenerator    = dataGeneratorIn;
        this.lootTableList    = lootTableListIn;
        this.lootParameterSet = lootParameterSetIn;
        this.name             = nameIn;
    }

    private static Path getPath(Path pathIn, ResourceLocation id) {
        return pathIn.resolve("data/" + id.getNamespace() + "/loot_tables/" + id.getPath() + ".json");
    }

    @Override
    public void run(HashCache cache) {
        final Path                             path = this.dataGenerator.getOutputFolder();
        final Map<ResourceLocation, LootTable> map  = Maps.newHashMap();

        this.getTables().forEach((lootParameterSetPair) -> lootParameterSetPair.getFirst().get().accept((resourceLocation, builder) -> {
            if (map.put(resourceLocation, builder.setParamSet(lootParameterSetPair.getSecond()).build()) != null) {
                throw new IllegalStateException("Duplicate loot table " + resourceLocation);
            }
        }));

        ValidationContext validationtracker = new ValidationContext(this.lootParameterSet, (resourceLocation) -> null, map::get);
        this.validate(map, validationtracker);
        Multimap<String, String> multimap = validationtracker.getProblems();

        if (!multimap.isEmpty()) {
            multimap.forEach((name, problem) -> DiaboloLib.logger.warn("Found validation problem in " + name + ": " + problem));
            throw new IllegalStateException("Failed to validate loot tables, see logs");
        } else {
            map.forEach((resourceLocation, lootTable) -> {
                Path path1 = getPath(path, resourceLocation);

                try {
                    DataProvider.save(GSON, cache, LootTables.serialize(lootTable), path1);
                } catch (IOException ioexception) {
                    DiaboloLib.logger.error("Couldn't save loot table {}", path1, ioexception);
                }

            });
        }
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
        map.forEach((resourceLocation, lootTable) -> LootTables.validate(validationtracker, resourceLocation, lootTable));
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return this.lootTableList;
    }

    @Override
    public String getName() {
        return this.name + " - LootTables";
    }
}
