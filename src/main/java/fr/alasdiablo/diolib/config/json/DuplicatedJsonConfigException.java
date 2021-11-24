package fr.alasdiablo.diolib.config.json;

public class DuplicatedJsonConfigException extends IllegalStateException {
    public DuplicatedJsonConfigException(String modID) {
        super(modID + " already exists as config !");
    }

    public DuplicatedJsonConfigException(String element, String modID) {
        super(element + " already exists in " + modID + " config !");
    }
}
