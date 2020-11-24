package fr.alasdiablo.diolib.config;

import fr.alasdiablo.diolib.DiaboloLib;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Config decaration class
 */
public class ModConfig {

    /**
     * Containing all default config
     */
    public static class DefaultConfig {
        public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec SPEC;

        public static final ForgeConfigSpec.BooleanValue AUTHOR_LOGGING_EVENT;

        static {
            BUILDER.comment("DiaboloLib Options.");
            BUILDER.push("default");
            AUTHOR_LOGGING_EVENT = BUILDER.comment("Firework spawn on author logging").define("authorFirework", true);
            BUILDER.pop();
            SPEC = BUILDER.build();
        }
    }

    /**
     * Containing all config related to mob anger and aggro
     */
    public static class MobAngerConfig {
        public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec SPEC;

        public static final ForgeConfigSpec.BooleanValue ZOMBIFIED_PIGLIN_ANGER;
        public static final ForgeConfigSpec.IntValue ZOMBIFIED_PIGLIN_ANGER_RANGE;
        public static final ForgeConfigSpec.BooleanValue ENDERMAN_ANGER;
        public static final ForgeConfigSpec.IntValue ENDERMAN_ANGER_RANGE;

        static {
            BUILDER.comment("DiaboloLib Mobs Utils Options.");
            BUILDER.push("mobs");
            ZOMBIFIED_PIGLIN_ANGER = BUILDER.comment("Zombified piglin anger on nether ore harvest").define("zombifiedPiglinAnger", true);
            ZOMBIFIED_PIGLIN_ANGER_RANGE = BUILDER.comment("Zombified piglin anger range on nether ore harvest").defineInRange("zombifiedPiglinAngerRange", 10, 0, 64);
            ENDERMAN_ANGER = BUILDER.comment("Enderman anger on end ore harvest").define("endermanAnger", true);
            ENDERMAN_ANGER_RANGE = BUILDER.comment("Enderman anger range on end ore harvest").defineInRange("endermanAngerRange", 10, 0, 64);
            BUILDER.pop();
            SPEC = BUILDER.build();
        }
    }

    /**
     * Function called by DiaboloLib during mod construction
     */
    public static void setup() {
        try {
            Files.createDirectory(Paths.get(FMLPaths.CONFIGDIR.get().toAbsolutePath().toString(), "diabololib"));
        } catch (FileAlreadyExistsException ignored) {
        } catch (IOException e) {
            DiaboloLib.logger.error("Failed to create DiaboloLib config directory.", e);
        }
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, MobAngerConfig.SPEC, "diabololib/mobs.toml");
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, DefaultConfig.SPEC, "diabololib/default.toml");
    }

}
