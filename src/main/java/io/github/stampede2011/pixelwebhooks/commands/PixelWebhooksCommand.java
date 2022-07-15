package io.github.stampede2011.pixelwebhooks.commands;

import com.lypaka.lypakautils.FancyText;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.server.command.CommandTreeBase;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PixelWebhooksCommand extends CommandTreeBase {

    public PixelWebhooksCommand() {

        addSubcommand(new ReloadCommand());
        addSubcommand(new TestCommand());

    }

    @Override
    public String getName() {

        return "pixelwebhooks";

    }

    @Override
    public List<String> getAliases() {

        List<String> a = new ArrayList<>();
        a.add("pixelwebhook");
        return a;

    }

    @Override
    public List<String> getTabCompletions (MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {

        List<String> cmds = new ArrayList<>();
        cmds.add("test");
        cmds.add("reload");
        return cmds;

    }

    @Override
    public String getUsage (ICommandSender sender) {

        return "/pixelwebhooks <reload|test>";

    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        if (args.length < 1) {

            sender.sendMessage(FancyText.getFormattedText(getUsage(sender)));

        } else {

            String arg = args[0];
            if (arg.equalsIgnoreCase("reload")) {

                ReloadCommand reloadCommand = new ReloadCommand();
                reloadCommand.execute(server, sender, args);

            } else if (arg.equalsIgnoreCase("test")) {

                TestCommand testCommand = new TestCommand();
                testCommand.execute(server, sender, args);

            } else {

                sender.sendMessage(FancyText.getFormattedText(getUsage(sender)));

            }

        }

    }

}
