package fr.alasdiablo.mods.lib;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(DioLib.MOD_ID)
public class DioLib {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "dio_lib";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public DioLib(IEventBus modEventBus, ModContainer modContainer) {}
}
