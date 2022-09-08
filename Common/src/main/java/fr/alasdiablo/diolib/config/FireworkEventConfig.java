package fr.alasdiablo.diolib.config;

import com.google.gson.JsonObject;
import fr.alasdiablo.diolib.DiaboloLibCommon;
import fr.alasdiablo.diolib.api.config.JsonConfig;
import org.jetbrains.annotations.NotNull;

public class FireworkEventConfig extends JsonConfig {

    private boolean firework;

    public FireworkEventConfig() {
        this.firework = true;
    }

    public boolean canContributorFirework() {
        return this.firework;
    }

    @Override
    protected void read(@NotNull JsonObject json) {
        this.firework = json.get("contributor_firework").getAsBoolean();
        DiaboloLibCommon.LOGGER.debug("Data change for contributor firework : " + this.firework);
    }

    @Override
    protected JsonObject write() {
        JsonObject json = new JsonObject();
        json.addProperty("contributor_firework", this.firework);
        return json;
    }

    @Override
    protected String getName() {
        return "contributor_firework";
    }
}
