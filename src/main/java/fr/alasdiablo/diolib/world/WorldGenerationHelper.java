package fr.alasdiablo.diolib.world;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.world.level.levelgen.feature.StructureFeature;

@SuppressWarnings("unused")
public class WorldGenerationHelper {
    public static void addNoiseAffectingFeature(StructureFeature<?> structureFeature) {
        if (structureFeature == null) throw new NullPointerException("configuredFeature is null");

        if (StructureFeature.NOISE_AFFECTING_FEATURES instanceof ImmutableList) {
            StructureFeature.NOISE_AFFECTING_FEATURES = Lists.newArrayList(StructureFeature.NOISE_AFFECTING_FEATURES);
        }

        StructureFeature.NOISE_AFFECTING_FEATURES.add(structureFeature);
    }
}
