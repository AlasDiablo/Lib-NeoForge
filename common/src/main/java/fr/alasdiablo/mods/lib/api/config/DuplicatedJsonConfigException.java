package fr.alasdiablo.mods.lib.api.config;

/**
 * Execution use during config loading
 */
public class DuplicatedJsonConfigException extends IllegalStateException {
    /**
     * Config file linked to a mod already exists
     *
     * @param modID Mod in question
     */
    public DuplicatedJsonConfigException(String modID) {
        super(modID + " already exists as config !");
    }

    /**
     * Config element already exists
     *
     * @param element Element in question
     * @param modID   Mod in cause
     */
    public DuplicatedJsonConfigException(String element, String modID) {
        super(element + " already exists in " + modID + " config !");
    }
}
