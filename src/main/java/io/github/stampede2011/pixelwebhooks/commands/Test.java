package io.github.stampede2011.pixelwebhooks.commands;

import com.pixelmongenerations.api.events.spawning.SpawnEvent;
import com.pixelmongenerations.common.entity.pixelmon.EntityPixelmon;
import io.github.stampede2011.pixelwebhooks.DiscordWebhook;
import io.github.stampede2011.pixelwebhooks.PixelWebhooks;
import io.github.stampede2011.pixelwebhooks.utils.Utilities;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;

import java.awt.*;
import java.io.IOException;

public class Test implements CommandExecutor {

    private PixelWebhooks plugin = PixelWebhooks.getInstance();

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

        if (!plugin.mainConfig.get().webhookURL.equals("")) {
            DiscordWebhook webhook = new DiscordWebhook(plugin.mainConfig.get().webhookURL);

            webhook.setAvatarUrl(parse(plugin.mainConfig.get().authorIconURL));

            webhook.setUsername(parse(plugin.mainConfig.get().authorUsername));

            webhook.setTts(false);

            webhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setTitle(parse(plugin.mainConfig.get().embedSettings.title))
                    .setDescription(parse(plugin.mainConfig.get().embedSettings.description))
                    .setColor(new Color(Integer.parseInt(plugin.mainConfig.get().embedSettings.colorHex.replace("#", ""), 16)))
                    .setThumbnail(parse(plugin.mainConfig.get().embedSettings.thumbnailIcon))
                    .setFooter(parse(plugin.mainConfig.get().embedSettings.footerText), parse(plugin.mainConfig.get().embedSettings.footerIconURL))
                    .setImage(parse(plugin.mainConfig.get().embedSettings.imageURL))
                    .setAuthor(parse(plugin.mainConfig.get().embedSettings.authorName), parse(plugin.mainConfig.get().embedSettings.authorURL), parse(plugin.mainConfig.get().embedSettings.authorIconURL))
                    .setUrl(parse(plugin.mainConfig.get().embedSettings.url)));
            try {
                webhook.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            src.sendMessage(Utilities.toText("&bSuccessfully sent a test Discord message!"));
        } else {
            src.sendMessage(Utilities.toText("&c&lERROR! &cYou must setup the Webhook URL in the config first!"));
        }

        return CommandResult.success();
    }

    public static CommandSpec build() {
        return CommandSpec.builder()
                .permission("pixelwebhooks.command.test.base")
                .executor(new Test())
                .build();
    }

    public static String parse(String msg) {

        return msg.replaceAll("%pokemon%", "Test")
                .replaceAll("%biome%", "Test")
                .replaceAll("%x%", "0")
                .replaceAll("%y%", "0")
                .replaceAll("%z%", "0");
    }
}