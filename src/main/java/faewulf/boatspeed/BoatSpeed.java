package faewulf.boatspeed;

import faewulf.boatspeed.utils.ConfigHandler;
import faewulf.boatspeed.utils.Configs;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoatSpeed implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("speedy-boat");
    public static Configs modConfigs;

    @Override
    public void onInitialize() {
        modConfigs = ConfigHandler.loadConfig();
        ConfigHandler.saveConfig(modConfigs);
    }
}