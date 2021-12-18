package fr.alasdiablo.diolib.world;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public enum ExtendedFillerBlockType {
    GRAVEL(Blocks.GRAVEL),
    BASALT(Blocks.BASALT),
    END_STONE(Blocks.END_STONE),
    SAND(Blocks.SAND),
    RED_SAND(Blocks.RED_SAND),
    GRANITE(Blocks.GRANITE),
    DIORITE(Blocks.DIORITE),
    ANDESITE(Blocks.ANDESITE);

    private static final Map<ResourceLocation, RuleTest> RULE_TEST_MAP = new HashMap<>();
    private final        Block                           block;

    ExtendedFillerBlockType(Block block) {
        this.block = block;
    }

    public static RuleTest createOrGetRuleTest(Block block) {
        ResourceLocation registryName = block.getRegistryName();
        if (RULE_TEST_MAP.containsKey(registryName))
            return RULE_TEST_MAP.get(registryName);
        else {
            RuleTest rule = new BlockMatchTest(block);
            RULE_TEST_MAP.put(registryName, rule);
            return rule;
        }
    }

    public RuleTest get() {
        return createOrGetRuleTest(this.block);
    }
}
