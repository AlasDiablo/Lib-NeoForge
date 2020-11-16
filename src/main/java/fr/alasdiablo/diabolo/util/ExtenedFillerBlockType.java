package fr.alasdiablo.diabolo.util;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;

public enum ExtenedFillerBlockType {
    GRAVEL(Blocks.GRAVEL),
    BASALT(Blocks.BASALT),
    END_STONE(Blocks.END_STONE);

    private final RuleTest fillerRule;

    ExtenedFillerBlockType(Block block) {
        this.fillerRule = new BlockMatchRuleTest(block);
    }

    public RuleTest get() {
        return fillerRule;
    }
}
