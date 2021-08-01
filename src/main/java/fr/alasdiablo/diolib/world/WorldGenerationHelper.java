package fr.alasdiablo.diolib.world;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import fr.alasdiablo.diolib.DiaboloLib;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ReplaceBlockConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import org.apache.logging.log4j.message.FormattedMessage;

import javax.annotation.Nullable;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class WorldGenerationHelper {

    /**
     * Function use for adding OreFeature to a biome
     *
     * @param biome             Biome which receives the ConfiguredFeature
     * @param configuredFeature ConfiguredFeature containing the OreFeature
     * @param decoration        The GenerationStage of the ConfiguredFeature
     */
    public static <FC extends FeatureConfiguration> void addFeature(Biome biome, @Nullable ConfiguredFeature<FC, ?> configuredFeature, GenerationStep.Decoration decoration) {
        if (configuredFeature == null) throw new NullPointerException("configuredFeature is null");

        if (biome.getGenerationSettings().features instanceof ImmutableList) {
            biome.getGenerationSettings().features = new ArrayList<>(biome.getGenerationSettings().features);
        }

        while (biome.getGenerationSettings().features.size() <= decoration.ordinal()) {
            biome.getGenerationSettings().features.add(Lists.newArrayList());
        }

        if (biome.getGenerationSettings().features.get(decoration.ordinal()) instanceof ImmutableList) {
            biome.getGenerationSettings().features.set(decoration.ordinal(), new ArrayList<>(biome.getGenerationSettings().features.get(decoration.ordinal())));
        }

        biome.getGenerationSettings().features.get(decoration.ordinal()).add(() -> configuredFeature);
    }

    public static void addNoiseAffectingFeature(StructureFeature<?> structureFeature) {
        if (structureFeature == null) throw new NullPointerException("configuredFeature is null");

        if (StructureFeature.NOISE_AFFECTING_FEATURES instanceof ImmutableList) {
            StructureFeature.NOISE_AFFECTING_FEATURES = new ArrayList<>(StructureFeature.NOISE_AFFECTING_FEATURES);
        }

        StructureFeature.NOISE_AFFECTING_FEATURES.add(structureFeature);
    }

    public static class ConfiguredFeatureHelper {

        /**
         * Default function use to register an ConfiguredFeature
         *
         * @param <FC>              The configuredFeature need to have an IFeatureConfig in the first element
         * @param name              RegistryName of the ConfiguredFeature
         * @param configuredFeature ConfiguredFeature need to be registered
         */
        public static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(ResourceLocation name, ConfiguredFeature<FC, ?> configuredFeature) {
            ConfiguredFeature<FC, ?> registeredFeature = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, name, configuredFeature);
            DiaboloLib.logger.debug(new FormattedMessage("ConfiguredFeature with the name '%s' was added to the Registry.", name.toString()));
            return registeredFeature;
        }

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
        public static ConfiguredFeature<?, ?> registerOreFeature(ResourceLocation name, RuleTest blockType, BlockState oreBlock, int size, int count, int bottom, int top) {
            ConfiguredFeature<?, ?> feature = ConfiguredFeatureHelper.register(
                    name,
                    Feature.ORE.configured(
                            new OreConfiguration(
                                    blockType,
                                    oreBlock,
                                    size
                            )
                    ).rangeUniform(
                            VerticalAnchor.absolute(bottom),
                            VerticalAnchor.absolute(top)
                    ).squared().count(count)
            );
            DiaboloLib.logger.debug(new FormattedMessage("OreFeature with the name '%s' was added to the Registry.", name.toString()));
            return feature;
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
        public static ConfiguredFeature<?, ?> registerReplaceBlockFeature(ResourceLocation name, BlockState replacementBlock, BlockState oreBlock, int count, int bottom, int top) {
            ConfiguredFeature<?, ?> feature = ConfiguredFeatureHelper.register(
                    name,
                    Feature.REPLACE_SINGLE_BLOCK.configured(
                            new ReplaceBlockConfiguration(
                                    replacementBlock,
                                    oreBlock
                            )
                    ).rangeUniform(
                            VerticalAnchor.absolute(bottom),
                            VerticalAnchor.absolute(top)
                    ).squared().count(count)
            );
            DiaboloLib.logger.debug(new FormattedMessage("ReplaceBlockFeature with the name '%s' was added to the Registry.", name.toString()));
            return feature;
        }
    }
}
