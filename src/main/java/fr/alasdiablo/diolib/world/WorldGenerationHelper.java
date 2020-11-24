package fr.alasdiablo.diolib.world;

import com.google.common.collect.Lists;
import fr.alasdiablo.diolib.DiaboloLib;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.logging.log4j.message.FormattedMessage;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class WorldGenerationHelper {

    /**
     * Function use for adding OreFeature to a biome
     *
     * @param biome             Biome which receives the ConfiguredFeature
     * @param configuredFeature ConfiguredFeature containing the OreFeature
     * @param decoration        The GenerationStage of the ConfiguredFeature
     */
    public static void addFeature(Biome biome, @Nullable ConfiguredFeature<?, ?> configuredFeature, GenerationStage.Decoration decoration) {
        if (configuredFeature == null) throw new NullPointerException("configuredFeature is null");

        List<List<Supplier<ConfiguredFeature<?, ?>>>> biomeFeatures = new ArrayList<>(biome.getGenerationSettings().getFeatures());
        while (biomeFeatures.size() <= decoration.ordinal()) {
            biomeFeatures.add(Lists.newArrayList());
        }
        List<Supplier<ConfiguredFeature<?, ?>>> features = new ArrayList<>(biomeFeatures.get(decoration.ordinal()));
        features.add(() -> configuredFeature);
        biomeFeatures.set(decoration.ordinal(), features);

        ObfuscationReflectionHelper.setPrivateValue(BiomeGenerationSettings.class, biome.getGenerationSettings(), biomeFeatures, "field_242484_f");
    }

    public static class ConfiguredFeatureHelper {
        /**
         * Function use for create a ConfiguredFeature (Ore vine)
         *
         * @param name      RegistryName of the ConfiguredFeature
         * @param blockType The type of block replaced by <i>oreBlock</i>
         * @param oreBlock  The ore block who replace <i>blockType</i>
         * @param size      The size of the vine
         * @param count     The number of vine in a chunk
         * @param bottom    The minimum height
         * @param top       The maximum height
         */
        public static void registerOreFeature(ResourceLocation name, RuleTest blockType, BlockState oreBlock, int size, int count, int bottom, int top) {
            Registry.register(
                    WorldGenRegistries.CONFIGURED_FEATURE,
                    name,
                    Feature.ORE.withConfiguration(
                            new OreFeatureConfig(
                                    blockType,
                                    oreBlock,
                                    size
                            )
                    ).withPlacement(
                            Placement.RANGE.configure(
                                    new TopSolidRangeConfig(
                                            bottom,
                                            0,
                                            top)
                            )
                    ).square().func_242731_b/* repeat */(count)
            );
            DiaboloLib.logger.debug(new FormattedMessage("OreFeature with the name '%s' was added to the Registry.", name.toString()));
        }

        /**
         * Function use for create a ConfiguredFeature (Replace a block)
         *
         * @param name             RegistryName of the ConfiguredFeature
         * @param replacementBlock The block replaced by <i>oreBlock</i>
         * @param oreBlock         The ore block who replace <i>replacementBlock</i>
         * @param count            The number of vine in a chunk
         * @param bottom           The minimum height
         * @param top              The maximum height
         */
        public static void registerReplaceBlockFeature(ResourceLocation name, BlockState replacementBlock, BlockState oreBlock, int count, int bottom, int top) {
            Registry.register(
                    WorldGenRegistries.CONFIGURED_FEATURE,
                    name,
                    Feature.EMERALD_ORE.withConfiguration(
                            new ReplaceBlockConfig(
                                    replacementBlock,
                                    oreBlock
                            )
                    ).withPlacement(
                            Placement.RANGE.configure(
                                    new TopSolidRangeConfig(
                                            bottom,
                                            0,
                                            top)
                            )
                    ).square().func_242731_b/* repeat */(count)
            );
            DiaboloLib.logger.debug(new FormattedMessage("ReplaceBlockFeature with the name '%s' was added to the Registry.", name.toString()));
        }
    }
}
