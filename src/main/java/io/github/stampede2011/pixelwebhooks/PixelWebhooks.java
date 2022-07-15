package io.github.stampede2011.pixelwebhooks;

import com.lypaka.betterpixelmonspawner.Utils.FancyText;
import com.lypaka.lypakautils.ConfigurationLoaders.BasicConfigManager;
import com.lypaka.lypakautils.ConfigurationLoaders.ConfigUtils;
import io.github.stampede2011.pixelwebhooks.commands.PixelWebhooksCommand;
import io.github.stampede2011.pixelwebhooks.config.ConfigGetters;
import io.github.stampede2011.pixelwebhooks.listeners.BPSLegendarySpawn;
import io.github.stampede2011.pixelwebhooks.listeners.LegendaryGeneratorSpawn;
import io.github.stampede2011.pixelwebhooks.listeners.LegendarySpawn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mod(
        modid = PixelWebhooks.ID,
        name = PixelWebhooks.NAME,
        version = PixelWebhooks.VERSION,
        acceptableRemoteVersions = "*",
        dependencies = "required-after:lypakautils;required-after:pixelmon"
)

public class PixelWebhooks {

    public static final String ID = "pixelwebhooks";
    public static final String NAME = "PixelWebhooks";
    public static final String AUTHORS = "Stampede2011, Lypaka";
    public static final String DESCRIPTION = "Send legendary spawn announcements to Discord through a Webhook!";
    public static final String VERSION = "1.1.1";
    public static Logger logger = LogManager.getLogger(ID);
    public static BasicConfigManager configManager;

    private static PixelWebhooks instance;

    @Mod.EventHandler
    public void onPreInit (FMLPreInitializationEvent event) throws IOException, ObjectMappingException {
        logger.info(FancyText.getFancyString("\n"
                + "&b _____ _         _ _ _ _     _   _           _ \n"
                + "&b|  _  |_|_ _ ___| | | | |___| |_| |_ ___ ___| |_ ___ \n"
                + "&b|   __| |_'_| -_| | | | | -_| . |   | . | . | '_|_ -| \n"
                + "&b|__|  |_|_,_|___|_|_____|___|___|_|_|___|___|_,_|___| \n"
                + "\n"
                + "&b" + PixelWebhooks.NAME + " " + PixelWebhooks.VERSION + " has been enabled!\n"));
        instance = this;
        Path dir = ConfigUtils.checkDir(Paths.get("./config/pixelwebhooks"));
        String[] files = new String[]{"PixelWebhooks.conf"};
        configManager = new BasicConfigManager(files, dir, PixelWebhooks.class, NAME, ID, logger);
        configManager.init();
        ConfigGetters.load();
    }

    @Mod.EventHandler
    public void onPostInit (FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new LegendarySpawn());
        if (Loader.isModLoaded("legendarygenerator")) {
            logger.info("PixelWebhooks detected that LegendaryGenerator is installed!");
            MinecraftForge.EVENT_BUS.register(new LegendaryGeneratorSpawn());
        }
        if (Loader.isModLoaded("betterpixelmonspawner")) {
            logger.info("PixelWebhooks detected that BetterPixelmonSpawner is installed!");
            MinecraftForge.EVENT_BUS.register(new BPSLegendarySpawn());
        }
    }

    @Mod.EventHandler
    public void onServerStarting (FMLServerStartingEvent event) {
        event.registerServerCommand(new PixelWebhooksCommand());
    }

    public static PixelWebhooks getInstance() {
        return instance;
    }

    public static Logger getLogger() {
        return logger;
    }

}