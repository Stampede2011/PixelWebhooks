package io.github.stampede2011.pixelwebhooks.commands;

import io.github.stampede2011.pixelwebhooks.PixelWebhooks;
import io.github.stampede2011.pixelwebhooks.utils.Utilities;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;

public class Reload implements CommandExecutor {

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

        PixelWebhooks.getInstance().mainConfig.reload();

        PixelWebhooks.getLogger().info("PixelWebhooks has been successfully reloaded!");
        src.sendMessage(Utilities.toText("&bPixelWebhooks has been successfully reloaded!"));

        return CommandResult.success();
    }

    public static CommandSpec build() {
        return CommandSpec.builder()
                .permission("pixelwebhooks.command.reload.base")
                .executor(new Reload())
                .build();
    }
}
