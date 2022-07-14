package io.github.stampede2011.pixelwebhooks.config;

import com.google.common.collect.Lists;
import com.pixelmongenerations.core.enums.EnumSpecies;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.List;
import java.util.stream.Collectors;

@ConfigSerializable
public class MainConfig {

    @Setting(value="webhook-url", comment = "URL for your Discord Webhook. Obtained from Integrations in Settings on your Discord server.")
    public String webhookURL = "";

    @Setting(value="announce-list", comment = "List of Pokemon to send a webhook for")
    public List<String> announceList = Lists.newArrayList(EnumSpecies.legendaries.stream().map(String::toLowerCase).collect(Collectors.toList()));

    @Setting(value="author-username")
    public String authorUsername = "Legend Alert";

    @Setting(value="author-icon-url")
    public String authorIconURL = "https://www.clipartmax.com/png/middle/129-1298491_pokeball-free-icon-pok%C3%A9-ball.png";

    @Setting(value="embed-settings", comment = "Settings for the sent Discord embed. Placeholders: %pokemon% %biome% %x% %y% %z%")
    public EmbedSettings embedSettings = new EmbedSettings();

    @ConfigSerializable
    public static class EmbedSettings {

        @Setting(value="title")
        public String title = "";

        @Setting(value="description")
        public String description = ":loudspeaker: The legendary Pokemon, %pokemon%, has spawned in the %biome% biome!";

        @Setting(value="color-hex", comment="Hex code value of the color code.")
        public String colorHex = "#ff8100";

        @Setting(value="thumbnail-icon-url")
        public String thumbnailIcon = "";

        @Setting(value="footer-text")
        public String footerText = "";

        @Setting(value="footer-icon-url")
        public String footerIconURL = "";

        @Setting(value="image-url")
        public String imageURL = "";

        @Setting(value="author-name")
        public String authorName = "";

        @Setting(value="author-url")
        public String authorURL = "";

        @Setting(value="author-icon-url")
        public String authorIconURL = "";

        @Setting(value="url")
        public String url = "";

    }

}
