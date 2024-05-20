package net.montal.skywarsremake.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.montal.skywarsremake.SkywarsRemake;
import net.montal.skywarsremake.utils.CC;
import org.bukkit.entity.Player;

@CommandAlias("skywars")
@CommandPermission("skywars.admin")
public class MainCommand extends BaseCommand {

    SkywarsRemake instance = SkywarsRemake.getInstance();

    @Default
    @Syntax("<args>")
    @CommandCompletion("reload")
    public void onMainCommand(Player user) {

        user.sendMessage(CC.translate("&cUse: /skywars (reload)"));

    }

    @Subcommand("reload")
    public void reload(Player user) {

        user.sendMessage(CC.translate("&aYou have successfully reloaded SkywarsRemake's configuration!"));
        instance.reloadConfig();

    }

}
