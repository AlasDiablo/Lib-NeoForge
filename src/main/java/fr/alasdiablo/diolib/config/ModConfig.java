package fr.alasdiablo.diolib.config;

import fr.alasdiablo.diolib.DiaboloLib;
import fr.alasdiablo.diolib.config.json.JsonConfigBuilder;

import java.io.IOException;

public class ModConfig {

    public static AngerConfig ENDERMAN_ANGER = new AngerConfig("enderman_anger");
    public static AngerConfig ZOMBIFIED_PIGLIN_ANGER = new AngerConfig("zombified_piglin_anger");
    public static FireworkEventConfig CONTRIBUTOR_FIREWORK = new FireworkEventConfig();

    public static void setup() {
        try {
            final JsonConfigBuilder configBuilder = new JsonConfigBuilder("diabololib");

            configBuilder.add(ENDERMAN_ANGER)
                    .add(ZOMBIFIED_PIGLIN_ANGER)
                    .add(CONTRIBUTOR_FIREWORK)
                    .build();
        } catch (IOException e) {
            DiaboloLib.logger.error("Diabolo Config have failed to setup, this can cause issues if not resolve !");
            DiaboloLib.logger.debug(e);
        }
    }
}
