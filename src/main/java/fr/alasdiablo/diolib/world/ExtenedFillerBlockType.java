package fr.alasdiablo.diolib.world;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public enum ExtenedFillerBlockType {
    GRAVEL(Blocks.GRAVEL),
    BASALT(Blocks.BASALT),
    END_STONE(Blocks.END_STONE),
    SAND(Blocks.SAND),
    RED_SAND(Blocks.RED_SAND),
    GRANITE(Blocks.GRANITE),
    DIORITE(Blocks.DIORITE),
    ANDESITE(Blocks.ANDESITE);

    private final Block block;

    ExtenedFillerBlockType(Block block) {
        this.block = block;
    }

    public RuleTest get() {
        return createOrGetRuleTest(this.block);
    }

    private static final Map<ResourceLocation, RuleTest> RULE_TEST_MAP = new HashMap<>();

    public static RuleTest createOrGetRuleTest(Block block) {
        ResourceLocation registryName = block.getRegistryName();
        if (RULE_TEST_MAP.containsKey(registryName))
            return RULE_TEST_MAP.get(registryName);
        else {
            RuleTest rule = new BlockMatchRuleTest(block);
            RULE_TEST_MAP.put(registryName, rule);
            return rule;
        }
    }
}
