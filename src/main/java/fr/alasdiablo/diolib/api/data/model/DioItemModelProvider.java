package fr.alasdiablo.diolib.api.data.model;

import fr.alasdiablo.diolib.api.util.ResourceLocations;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public abstract class DioItemModelProvider extends ItemModelProvider {
    public DioItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    private void itemBlock(@NotNull String block) {
        this.withExistingParent(block, ResourceLocations.of(this.modid, "block/" + block));
    }

    private void item(@NotNull String item) {
        withExistingParent(item, new ResourceLocation("item/generated")).texture("layer0", ResourceLocations.of(this.modid, "item/" + item));
    }
}
