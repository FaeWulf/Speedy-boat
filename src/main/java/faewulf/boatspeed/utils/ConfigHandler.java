package faewulf.boatspeed.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import faewulf.boatspeed.BoatSpeed;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigHandler {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File("config/speedy_boat.json");

    public static Configs loadConfig() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                return GSON.fromJson(reader, Configs.class);
            } catch (IOException e) {
                BoatSpeed.LOGGER.error(e.toString());
                return new Configs();
            }
        } else {
            return new Configs();
        }
    }

    public static void saveConfig(Configs config) {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(config, writer);
        } catch (IOException e) {
            BoatSpeed.LOGGER.error(e.toString());
        }
    }
}
