package fr.alasdiablo.diolib.data.provider;

import com.google.common.collect.Lists;
import fr.alasdiablo.diolib.data.advancement.AdvancementCategory;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * Advancement Provider used to create advancement during gather data event
 */
@SuppressWarnings("unused")
public abstract class DioAdvancementProvider extends AdvancementProvider {

    private final String modId;

    /**
     * Default constructor
     *
     * @param generatorIn        Instance of the data generator
     * @param existingFileHelper Instance of the file helper
     */
    public DioAdvancementProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper, String modId) {
        super(generatorIn, existingFileHelper);
        this.tabs  = Lists.newArrayList();
        this.modId = modId;
    }

    /**
     * Function used to add an advancement
     *
     * @param advancement Add the advancement into the list of advancement
     */
    public void addAdvancement(AdvancementCategory advancement) {
        this.tabs.add(advancement);
    }

    /**
     * Function used to add advancement before registerAdvancements(Consumer<Advancement> consumer, net.minecraftforge.common.data.ExistingFileHelper fileHelper)
     */
    public abstract void registerAdvancements();

    /**
     * Call registerAdvancements and register it (super call)
     */
    @Override
    protected void registerAdvancements(
            @NotNull Consumer<Advancement> consumer, @NotNull ExistingFileHelper fileHelper
    ) {
        this.registerAdvancements();
        super.registerAdvancements(consumer, fileHelper);
    }

    @Override
    public @NotNull String getName() {
        return "Advancements: " + this.modId;
    }
}
