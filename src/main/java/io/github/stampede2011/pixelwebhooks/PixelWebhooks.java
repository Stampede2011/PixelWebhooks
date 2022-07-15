package io.github.stampede2011.pixelwebhooks;

import com.google.inject.Inject;
import io.github.eufranio.config.Config;
import io.github.stampede2011.pixelwebhooks.commands.Base;
import io.github.stampede2011.pixelwebhooks.config.MainConfig;
import io.github.stampede2011.pixelwebhooks.listeners.BPSLegendarySpawn;
import io.github.stampede2011.pixelwebhooks.listeners.LegendaryGeneratorSpawn;
import io.github.stampede2011.pixelwebhooks.listeners.LegendarySpawn;
import io.github.stampede2011.pixelwebhooks.utils.Utilities;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;

import java.io.File;

@Plugin(id = PixelWebhooks.ID,
        name = PixelWebhooks.NAME,
        authors = PixelWebhooks.AUTHORS,
        description = PixelWebhooks.DESCRIPTION,
        version = PixelWebhooks.VERSION,
        dependencies = {
                @Dependency(id = "pixelmon", optional = false)
        }
)

public class PixelWebhooks {

    public static final String ID = "pixelwebhooks";
    public static final String NAME = "PixelWebhooks";
    public static final String AUTHORS = "Stampede2011, Lypaka";
    public static final String DESCRIPTION = "Send legendary spawn announcements to Discord through a Webhook!";
    public static final String VERSION = "1.1.1";

    @Inject
    private Logger logger;

    @Inject
    @ConfigDir(sharedRoot = false)
    public File dir;

    public Config<MainConfig> mainConfig;

    private static PixelWebhooks instance;

    @Listener
    public void onGameInit(GameInitializationEvent e) {
        instance = this;

        Sponge.getServer().getConsole().sendMessage(Utilities.toText("\n"
                + "§b _____ _         _ _ _ _     _   _           _ \n"
                + "§b|  _  |_|_ _ ___| | | | |___| |_| |_ ___ ___| |_ ___ \n"
                + "§b|   __| |_'_| -_| | | | | -_| . |   | . | . | '_|_ -| \n"
                + "§b|__|  |_|_,_|___|_|_____|___|___|_|_|___|___|_,_|___| \n"
                + "\n"
                + "&b" + PixelWebhooks.NAME + " " + PixelWebhooks.VERSION + " has been enabled!\n"));
    }

    @Listener
    public void onServerStarted(GameStartedServerEvent e) {
        this.mainConfig = new Config<>(MainConfig.class, "PixelWebhooks.conf", dir);

        MinecraftForge.EVENT_BUS.register(new LegendarySpawn());

        if (Sponge.getPluginManager().getPlugin("legendarygenerator").isPresent()) {
            this.logger.info("PixelWebhooks detected that LegendaryGenerator is installed!");
            MinecraftForge.EVENT_BUS.register(new LegendaryGeneratorSpawn());
        }
        if (Loader.isModLoaded("betterpixelmonspawner")) {
            logger.info("PixelWebhooks detected that BetterPixelmonSpawner is installed!");
            MinecraftForge.EVENT_BUS.register(new BPSLegendarySpawn());
        }

        Sponge.getCommandManager().register(instance, Base.build(), "pixelwebhooks", "pixelwebhook");
    }

    @Listener
    public void onReload(GameReloadEvent e) {
        this.mainConfig.reload();
    }

    public static PixelWebhooks getInstance() {
        return instance;
    }

    public static Logger getLogger() {
        return instance.logger;
    }

}