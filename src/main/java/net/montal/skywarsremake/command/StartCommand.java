package net.montal.skywarsremake.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.montal.skywarsremake.SkywarsRemake;
import net.montal.skywarsremake.tasks.GameStartCooldownTask;
import net.montal.skywarsremake.utils.CC;
import org.bukkit.entity.Player;

@CommandAlias("startgame")
@CommandPermission("skywars.admin")
public class StartCommand extends BaseCommand {

    @Default
    public void startGame(Player user) {

        user.sendMessage(CC.translate("&aYou have forced the game to start!"));
        new GameStartCooldownTask(SkywarsRemake.getInstance().getGameStateManager()).startCooldown();

    }

}
