package io.github.stampede2011.pixelwebhooks.config;

import com.google.common.reflect.TypeToken;
import io.github.stampede2011.pixelwebhooks.PixelWebhooks;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.util.List;

public class ConfigGetters {

    public static List<String> announceList;
    public static String authorIconURL;
    public static String authorUsername;

    public static String embedAuthorIconURL;
    public static String embedAuthorName;
    public static String embedAuthorURL;
    public static String embedHexColor;
    public static String embedDescription;
    public static String embedFooterIconURL;
    public static String embedFooterText;
    public static String embedImageURL;
    public static String embedThumbnailIconURL;
    public static String embedTitle;
    public static String embedURL;

    public static String webhookURL;

    public static void load() throws ObjectMappingException {

        announceList = PixelWebhooks.configManager.getConfigNode(0, "config", "announce-list").getList(TypeToken.of(String.class));
        authorIconURL = PixelWebhooks.configManager.getConfigNode(0, "config", "author-icon-url").getString();
        authorUsername = PixelWebhooks.configManager.getConfigNode(0, "config", "author-username").getString();

        embedAuthorIconURL = PixelWebhooks.configManager.getConfigNode(0, "config", "embed-settings", "author-icon-url").getString();
        embedAuthorName = PixelWebhooks.configManager.getConfigNode(0, "config", "embed-settings", "author-name").getString();
        embedAuthorURL = PixelWebhooks.configManager.getConfigNode(0, "config", "embed-settings", "author-url").getString();
        embedHexColor = PixelWebhooks.configManager.getConfigNode(0, "config", "embed-settings", "color-hex").getString();
        embedDescription = PixelWebhooks.configManager.getConfigNode(0, "config", "embed-settings", "description").getString();
        embedFooterIconURL = PixelWebhooks.configManager.getConfigNode(0, "config", "embed-settings", "footer-icon-url").getString();
        embedFooterText = PixelWebhooks.configManager.getConfigNode(0, "config", "embed-settings", "footer-text").getString();
        embedImageURL = PixelWebhooks.configManager.getConfigNode(0, "config", "embed-settings", "image-url").getString();
        embedThumbnailIconURL = PixelWebhooks.configManager.getConfigNode(0, "config", "embed-settings", "thumbnail-icon-url").getString();
        embedTitle = PixelWebhooks.configManager.getConfigNode(0, "config", "embed-settings", "title").getString();
        embedURL = PixelWebhooks.configManager.getConfigNode(0, "config", "embed-settings", "url").getString();
        webhookURL = PixelWebhooks.configManager.getConfigNode(0, "config", "webhook-url").getString();

    }

}
