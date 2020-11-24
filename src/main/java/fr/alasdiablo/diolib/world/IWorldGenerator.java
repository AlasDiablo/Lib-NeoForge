package fr.alasdiablo.diolib.world;

import net.minecraft.world.biome.Biome;

/**
 * Default interface of a World Generator
 */
public interface IWorldGenerator {
    /**
     * Function use to add all ConfiguredFeature to a biome
     * @param biome Biome which receives the ConfiguredFeature
     */
    void startWorldGeneration(Biome biome);
}
