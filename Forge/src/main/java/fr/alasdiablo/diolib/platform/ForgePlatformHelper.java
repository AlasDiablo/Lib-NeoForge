package fr.alasdiablo.diolib.platform;

import fr.alasdiablo.diolib.api.item.GroundCreativeModeTab;
import fr.alasdiablo.diolib.platform.services.IPlatformHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.loading.FMLPaths;
import org.jetbrains.annotations.NotNull;

public class ForgePlatformHelper implements IPlatformHelper {
    @Override
    public String getConfigPath() {
        return FMLPaths.CONFIGDIR.get().toAbsolutePath().toString();
    }

    @Override
    public CreativeModeTab createTab(ItemStack itemStack, String modid, String label) {
        return new GroundCreativeModeTab(modid + "." + label) {
            @Override
            public @NotNull ItemStack makeIcon() {
                return itemStack;
            }
        };
    }
}
