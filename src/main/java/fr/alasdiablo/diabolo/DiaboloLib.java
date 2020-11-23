package fr.alasdiablo.diabolo;

import fr.alasdiablo.diabolo.config.ModConfig;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DiaboloLib.MOD_ID)
public class DiaboloLib {

    public static final String MOD_ID = "diabolo";

    public static final Logger logger = LogManager.getLogger(MOD_ID);

    public DiaboloLib() {
        ModConfig.setup();
    }
}
