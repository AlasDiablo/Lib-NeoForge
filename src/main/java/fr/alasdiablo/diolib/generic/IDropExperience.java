package fr.alasdiablo.diolib.generic;

import net.minecraft.util.valueproviders.UniformInt;

import java.util.Random;

/**
 * Interface use by an Entity, Block or Other Element who drop xp
 */
@SuppressWarnings("unused")
public interface IDropExperience {
    /**
     * Getter use by <i>getExperience</i> for get the <i>ExperienceRarity</i>
     *
     * @return Return the <i>ExperienceRarity</i> set by an Entity, Block or Other Element
     *
     * @see ExperienceRarity
     */
    ExperienceRarity getExperienceRarity();

    /**
     * Default implantation of the xp range droped by a block
     *
     * @param random         Math function use for generate random number
     * @param dropExperience An Entity, Block or Other Element who implement <i>IDropExperience</i>
     *
     * @return Return the a random quantity of xp corresponding to <i>an Entity, Block or Other Element</i>
     */
    default int getExperience(Random random, IDropExperience dropExperience) {
        return switch (dropExperience.getExperienceRarity()) {
            case COMMON -> UniformInt.of(0, 2).sample(random);
            case UNCOMMON -> UniformInt.of(2, 5).sample(random);
            case RARE -> UniformInt.of(3, 7).sample(random);
            default -> -1;
        };
    }
}
