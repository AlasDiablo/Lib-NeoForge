package fr.alasdiablo.diabolo.block;

import net.minecraft.util.math.MathHelper;

import java.util.Random;

/**
 * Interface use by block who drop xp
 */
public interface IDropExperience {
    /**
     * Getter use by <i>getExperience</i> for get the <i>ExperienceRarity</i>
     *
     * @return Return the <i>ExperienceRarity</i> set by the block
     * @see fr.alasdiablo.diabolo.block.ExperienceRarity
     */
    ExperienceRarity getExperienceRarity();

    /**
     * Default implantation of the xp range droped by a block
     *
     * @param random Math function use for generate random number
     * @param block  block who implement <i>IDropExperience</i>
     * @return Return the a random quantity of xp corresponding to <i>block</i>
     */
    default int getExperience(Random random, IDropExperience block) {
        switch (block.getExperienceRarity()) {
            case COMMON:
                return MathHelper.nextInt(random, 0, 2);
            case UNCOMMON:
                return MathHelper.nextInt(random, 2, 5);
            case RARE:
                return MathHelper.nextInt(random, 3, 7);
            default:
                return -1;
        }
    }
}
