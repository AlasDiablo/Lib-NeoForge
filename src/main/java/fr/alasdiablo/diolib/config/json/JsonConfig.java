package fr.alasdiablo.diolib.config.json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import fr.alasdiablo.diolib.DiaboloLib;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public abstract class JsonConfig {

    private Path filePath;

    public JsonConfig() {
        this.filePath = null;
    }

    void initOrLoad() throws IOException, IllegalStateException {
        if (filePath == null) {
            throw new IllegalStateException("File path of " + this.getName() + " is null !");
        } else {
            try {
                FileReader fileReader = new FileReader(this.filePath.toString());
                JsonParser jsonParser = new JsonParser();
                JsonObject json = jsonParser.parse(fileReader).getAsJsonObject();
                this.read(json);
                fileReader.close();
                DiaboloLib.logger.debug(String.format("Config %s have been loaded", this.getName()));
            } catch (FileNotFoundException e) {
                this.preWrite();
                DiaboloLib.logger.debug(String.format("Config %s have been write and loaded", this.getName()));
            } catch (JsonParseException | IllegalArgumentException | NullPointerException e) {
                DiaboloLib.logger.warn(String.format("Error during config initialization on %s cause by: %s", this.getName(), e.getLocalizedMessage()));
                this.preWrite();
                DiaboloLib.logger.debug(String.format("Config %s have been rewrite and loaded", this.getName()));
            }
        }
    }

    private void postWrite(JsonObject json) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath.toString());
        fileWriter.write(json.toString());
        fileWriter.flush();
        fileWriter.close();
    }

    private void preWrite() throws IOException, IllegalStateException {
        if (filePath == null) {
            throw new IllegalStateException("File path of " + this.getName() + " is null !");
        } else {
            JsonObject json = this.write();
            this.postWrite(json);
        }
    }

    /**
     * Processes this json file
     * @param json Json file need to be processes
     */
    protected abstract void read(JsonObject json);

    /**
     * Create the json object need to be write in the config directory
     * @return JsonObject need to be written
     */
    protected abstract JsonObject write();

    protected abstract String getName();

    void filePath(Path path) {
        this.filePath = path;
    }

}
