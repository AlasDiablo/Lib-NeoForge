package fr.alasdiablo.diolib.platform;

import fr.alasdiablo.diolib.api.util.DateRange;
import fr.alasdiablo.diolib.platform.services.IPlatformHelper;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class FabricPlatformHelper implements IPlatformHelper {
    @Override
    public String getConfigPath() {
        return FabricLoader.getInstance().getConfigDir().toAbsolutePath().toString();
    }

    @Override
    public CreativeModeTab createTab(ItemStack itemStack, String modid, String label) {
        CreativeModeTab toReturn = FabricItemGroupBuilder.create(new ResourceLocation(modid, "label")).icon(() -> itemStack).build();
        if (DateRange.IS_WINTER) {
            toReturn.setBackgroundSuffix("ground_winter.png");
        } else {
            toReturn.setBackgroundSuffix("ground.png");
        }
        if (DateRange.IS_APRIL_FIRST) {
            toReturn.setBackgroundSuffix("ground_april.png");
        }
        return toReturn;
    }
}
