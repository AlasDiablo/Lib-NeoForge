package fr.alasdiablo.diolib;

import fr.alasdiablo.diolib.event.FireworkEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DiaboloLibCommon.MOD_ID)
public class DiaboloLibForge {

    public DiaboloLibForge() {
        DiaboloLibCommon.init();
        MinecraftForge.EVENT_BUS.addListener(FireworkEvent.FIREWORK_EVENT::onEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent commonSetupEvent) {

    }
}
