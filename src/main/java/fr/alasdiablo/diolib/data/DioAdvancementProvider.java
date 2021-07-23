package fr.alasdiablo.diolib.data;

import net.minecraft.advancements.Advancement;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;

import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class DioAdvancementProvider extends AdvancementProvider {
    public DioAdvancementProvider(DataGenerator generatorIn, List<Consumer<Consumer<Advancement>>> advancements) {
        super(generatorIn);
        this.tabs = advancements;
    }
}
