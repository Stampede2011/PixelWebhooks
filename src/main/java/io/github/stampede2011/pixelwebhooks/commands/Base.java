package io.github.stampede2011.pixelwebhooks.commands;

import io.github.stampede2011.pixelwebhooks.utils.Utilities;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.service.pagination.PaginationList;

public class Base implements CommandExecutor {

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

        PaginationList.builder()
                .title(Utilities.toText("&b&lPixelWebhooks"))
                .padding(Utilities.toText("&8&m-&r"))
                .contents(
                        Utilities.toText("&b/pixelwebhooks reload &8- &fReloads PixelWebhooks"),
                        Utilities.toText("&b/pixelwebhooks test &8- &fSends a test message")
                )
                .linesPerPage(10)
                .sendTo(src);

        return CommandResult.success();
    }

    public static CommandSpec build() {
        return CommandSpec.builder()
                .permission("pixelwebhooks.command.base")
                .executor(new Base())
                .child(Reload.build(), "reload")
                .child(Test.build(), "test")
                .build();
    }
}