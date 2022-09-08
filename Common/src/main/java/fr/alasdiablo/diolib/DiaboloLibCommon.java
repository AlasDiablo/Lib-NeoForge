package fr.alasdiablo.diolib;

import fr.alasdiablo.diolib.api.tag.DioTags;
import fr.alasdiablo.diolib.config.DiaboloLibConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiaboloLibCommon {

    public static final String MOD_ID = "diolib";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void init() {
        DiaboloLibConfig.init();
        DioTags.init();
    }
}
