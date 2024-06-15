package fr.alasdiablo.diolib.config;

import fr.alasdiablo.diolib.Constants;
import fr.alasdiablo.diolib.api.config.JsonConfigBuilder;

import java.io.IOException;

public class DiaboloLibConfig {

    public final static AngerConfig         ENDERMAN_ANGER         = new AngerConfig("enderman_anger");
    public final static AngerConfig         ZOMBIFIED_PIGLIN_ANGER = new AngerConfig("zombified_piglin_anger");
    public final static FireworkEventConfig CONTRIBUTOR_FIREWORK   = new FireworkEventConfig();

    public static void init() {
        try {
            final JsonConfigBuilder configBuilder = new JsonConfigBuilder("diabololib");

            configBuilder.add(ENDERMAN_ANGER)
                    .add(ZOMBIFIED_PIGLIN_ANGER)
                    .add(CONTRIBUTOR_FIREWORK)
                    .build();
        } catch (IOException e) {
            Constants.LOG.error("Diabolo Config have failed to setup, this can cause issues if not resolve !");
            Constants.LOG.debug(String.valueOf(e));
        }
    }
}
