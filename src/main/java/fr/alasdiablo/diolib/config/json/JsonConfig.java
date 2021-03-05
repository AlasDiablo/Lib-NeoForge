package fr.alasdiablo.diolib.config.json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.file.Path;

public abstract class JsonConfig {

    private Path filePath;

    public JsonConfig() {
        this.filePath = null;
    }

    void preRead() throws IOException, IllegalStateException {
        if (filePath == null) {
            throw new IllegalStateException("File path of " + this.getName() + " is null !");
        } else {
            try {
                FileReader fileReader = new FileReader(this.filePath.toString());
                JsonParser jsonParser = new JsonParser();
                JsonObject json = jsonParser.parse(fileReader).getAsJsonObject();
                this.read(json);
                fileReader.close();
            } catch (FileNotFoundException e) {
                this.preWrite();
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

    protected abstract void read(JsonObject json);
    protected abstract JsonObject write();

    protected abstract String getName();

    void filePath(Path path) {
        this.filePath = path;
    }

}
