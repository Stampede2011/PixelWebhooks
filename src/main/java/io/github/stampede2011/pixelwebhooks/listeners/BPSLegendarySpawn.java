package io.github.stampede2011.pixelwebhooks.listeners;

import com.lypaka.betterpixelmonspawner.API.Spawning.LegendarySpawnEvent;
import com.pixelmongenerations.common.entity.pixelmon.EntityPixelmon;
import io.github.stampede2011.pixelwebhooks.DiscordWebhook;
import io.github.stampede2011.pixelwebhooks.config.ConfigGetters;
import io.github.stampede2011.pixelwebhooks.utils.Utilities;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.io.IOException;
import java.util.stream.Collectors;

public class BPSLegendarySpawn {

    @SubscribeEvent
    public void onBPSLegendary (LegendarySpawnEvent event) {

        if (!ConfigGetters.webhookURL.equals("")) {

            EntityPixelmon pokemon = event.getPokemon();
            if (ConfigGetters.announceList.stream().map(String::toLowerCase).collect(Collectors.toList()).contains(pokemon.getPokemonName().toLowerCase())) {

                DiscordWebhook webhook = new DiscordWebhook(ConfigGetters.webhookURL);

                webhook.setAvatarUrl(Utilities.parse(ConfigGetters.authorIconURL, event));

                webhook.setUsername(Utilities.parse(ConfigGetters.authorUsername, event));

                webhook.setTts(false);

                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle(Utilities.parse(ConfigGetters.embedTitle, event))
                        .setDescription(Utilities.parse(ConfigGetters.embedDescription, event))
                        .setColor(new Color(Integer.parseInt(ConfigGetters.embedHexColor.replace("#", ""), 16)))
                        .setThumbnail(Utilities.parse(ConfigGetters.embedThumbnailIconURL, event))
                        .setFooter(Utilities.parse(ConfigGetters.embedFooterText, event), Utilities.parse(ConfigGetters.embedFooterIconURL, event))
                        .setImage(Utilities.parse(ConfigGetters.embedImageURL, event))
                        .setAuthor(Utilities.parse(ConfigGetters.embedAuthorName, event), Utilities.parse(ConfigGetters.embedAuthorURL, event), Utilities.parse(ConfigGetters.embedAuthorIconURL, event))
                        .setUrl(Utilities.parse(ConfigGetters.embedURL, event)));
                try {
                    webhook.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }

}
