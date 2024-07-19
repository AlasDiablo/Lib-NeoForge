package fr.alasdiablo.mods.lib.config;

import fr.alasdiablo.mods.lib.Constants;
import fr.alasdiablo.mods.lib.api.config.JsonConfigBuilder;

import java.io.IOException;

public class DiaboloLibConfig {

    public final static AngerConfig         ENDERMAN_ANGER         = new AngerConfig("enderman_anger");
    public final static AngerConfig         ZOMBIFIED_PIGLIN_ANGER = new AngerConfig("zombified_piglin_anger");

    public static void init() {
        try {
            final JsonConfigBuilder configBuilder = new JsonConfigBuilder("diabololib");

            configBuilder.add(ENDERMAN_ANGER)
                    .add(ZOMBIFIED_PIGLIN_ANGER)
                    .build();
        } catch (IOException e) {
            Constants.LOG.error("Diabolo Config have failed to setup, this can cause issues if not resolve !");
            Constants.LOG.debug(String.valueOf(e));
        }
    }
}
