package io.github.stampede2011.pixelwebhooks.commands;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.PermissionHandler;
import com.pixelmongenerations.common.entity.pixelmon.EntityPixelmon;
import io.github.stampede2011.pixelwebhooks.DiscordWebhook;
import io.github.stampede2011.pixelwebhooks.config.ConfigGetters;
import io.github.stampede2011.pixelwebhooks.utils.Utilities;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

import java.awt.*;
import java.io.IOException;

public class TestCommand extends CommandBase {

    @Override
    public String getName() {

        return "test";

    }

    @Override
    public String getUsage (ICommandSender sender) {

        return "/pixelwebhook test";

    }

    @Override
    public void execute (MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        if (sender instanceof EntityPlayerMP) {

            EntityPlayerMP player = (EntityPlayerMP) sender;
            if (!PermissionHandler.hasPermission(player, "pixelwebhooks.command.test.base")) {

                player.sendMessage(FancyText.getFormattedText("&cYou don't have permission to use this command!"));
                return;

            }

        }

        if (!ConfigGetters.webhookURL.equals("")) {

            DiscordWebhook webhook = new DiscordWebhook(ConfigGetters.webhookURL);

            webhook.setAvatarUrl(parse(ConfigGetters.authorIconURL));

            webhook.setUsername(parse(ConfigGetters.authorUsername));

            webhook.setTts(false);

            webhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setTitle(parse(ConfigGetters.embedTitle))
                    .setDescription(parse(ConfigGetters.embedDescription))
                    .setColor(new Color(Integer.parseInt(ConfigGetters.embedHexColor.replace("#", ""), 16)))
                    .setThumbnail(parse(ConfigGetters.embedThumbnailIconURL))
                    .setFooter(parse(ConfigGetters.embedFooterText), parse(ConfigGetters.embedFooterIconURL))
                    .setImage(parse(ConfigGetters.embedImageURL))
                    .setAuthor(parse(ConfigGetters.embedAuthorName), parse(ConfigGetters.embedAuthorURL), parse(ConfigGetters.embedAuthorIconURL))
                    .setUrl(parse(ConfigGetters.embedURL)));
            try {
                webhook.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            sender.sendMessage(FancyText.getFormattedText("&bSuccessfully sent a test Discord message!"));

        } else {

            sender.sendMessage(FancyText.getFormattedText("&c&lERROR! &cYou must setup the Webhook URL in the config first!"));

        }

    }

    public static String parse(String msg) {

        return msg.replaceAll("%pokemon%", "Test")
                .replaceAll("%biome%", "Test")
                .replaceAll("%x%", "0")
                .replaceAll("%y%", "0")
                .replaceAll("%z%", "0");
    }

}
