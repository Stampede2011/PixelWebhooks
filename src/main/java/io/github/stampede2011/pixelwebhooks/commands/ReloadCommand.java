package io.github.stampede2011.pixelwebhooks.commands;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.PermissionHandler;
import io.github.stampede2011.pixelwebhooks.PixelWebhooks;
import io.github.stampede2011.pixelwebhooks.config.ConfigGetters;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

public class ReloadCommand extends CommandBase {

    @Override
    public String getName() {

        return "reload";

    }

    @Override
    public String getUsage (ICommandSender sender) {

        return "/pixelwebhook reload";

    }

    @Override
    public void execute (MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        if (sender instanceof EntityPlayerMP) {

            EntityPlayerMP player = (EntityPlayerMP) sender;
            if (!PermissionHandler.hasPermission(player, "pixelwebhooks.command.reload.base")) {

                player.sendMessage(FancyText.getFormattedText("&cYou do not have permission to use this command!"));
                return;

            }

        }

        try {

            PixelWebhooks.configManager.load();
            ConfigGetters.load();
            sender.sendMessage(FancyText.getFormattedText("&bPixelWebhooks has been successfully reloaded!"));

        } catch (ObjectMappingException e) {

            e.printStackTrace();

        }

    }

}
