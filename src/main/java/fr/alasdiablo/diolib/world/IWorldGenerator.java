package fr.alasdiablo.diolib.world;

import net.minecraft.world.level.biome.Biome;

/**
 * Default interface of a World Generator
 */
@SuppressWarnings("unused")
public interface IWorldGenerator {
    /**
     * Function used to add all ConfiguredFeature to a biome
     *
     * @param biome Biome which receives the ConfiguredFeature
     */
    void startWorldGeneration(Biome biome);
}
