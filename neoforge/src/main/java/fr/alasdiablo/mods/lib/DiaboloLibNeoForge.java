package fr.alasdiablo.mods.lib;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@SuppressWarnings("unused")
@Mod(Constants.MOD_ID)
public class DiaboloLibNeoForge {
    public DiaboloLibNeoForge(IEventBus eventBus) {
        CommonClass.init();
    }
}