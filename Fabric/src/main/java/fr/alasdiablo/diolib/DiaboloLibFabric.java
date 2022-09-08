package fr.alasdiablo.diolib;

import net.fabricmc.api.ModInitializer;

public class DiaboloLibFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        DiaboloLibCommon.init();
    }
}
