package io.github.stampede2011.pixelwebhooks.listeners;


import com.lypaka.betterpixelmonspawner.API.Spawning.LegendarySpawnEvent;
import com.pixelmongenerations.common.entity.pixelmon.EntityPixelmon;
import io.github.eufranio.config.Config;
import io.github.stampede2011.pixelwebhooks.DiscordWebhook;
import io.github.stampede2011.pixelwebhooks.PixelWebhooks;
import io.github.stampede2011.pixelwebhooks.config.MainConfig;
import io.github.stampede2011.pixelwebhooks.utils.Utilities;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.io.IOException;
import java.util.stream.Collectors;

public class BPSLegendarySpawn {

    @SubscribeEvent
    public void onBPSLegendary(LegendarySpawnEvent event) {

        Config<MainConfig> mainConfig = PixelWebhooks.getInstance().mainConfig;

        if (!mainConfig.get().webhookURL.equals("")) {

            EntityPixelmon pokemon = event.getPokemon();
            if (mainConfig.get().announceList.stream().map(String::toLowerCase).collect(Collectors.toList()).contains(pokemon.getPokemonName().toLowerCase())) {

                DiscordWebhook webhook = new DiscordWebhook(mainConfig.get().webhookURL);

                webhook.setAvatarUrl(Utilities.parse(mainConfig.get().authorIconURL, event));

                webhook.setUsername(Utilities.parse(mainConfig.get().authorUsername, event));

                webhook.setTts(false);

                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle(Utilities.parse(mainConfig.get().embedSettings.title, event))
                        .setDescription(Utilities.parse(mainConfig.get().embedSettings.description, event))
                        .setColor(new Color(Integer.parseInt(mainConfig.get().embedSettings.colorHex.replace("#", ""), 16)))
                        .setThumbnail(Utilities.parse(mainConfig.get().embedSettings.thumbnailIcon, event))
                        .setFooter(Utilities.parse(mainConfig.get().embedSettings.footerText, event), Utilities.parse(mainConfig.get().embedSettings.footerIconURL, event))
                        .setImage(Utilities.parse(mainConfig.get().embedSettings.imageURL, event))
                        .setAuthor(Utilities.parse(mainConfig.get().embedSettings.authorName, event), Utilities.parse(mainConfig.get().embedSettings.authorURL, event), Utilities.parse(mainConfig.get().embedSettings.authorIconURL, event))
                        .setUrl(Utilities.parse(mainConfig.get().embedSettings.url, event)));
                try {
                    webhook.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }

}