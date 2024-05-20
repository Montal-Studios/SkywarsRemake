package net.montal.skywarsremake.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.montal.skywarsremake.manager.chests.ChestManager;
import net.montal.skywarsremake.utils.CC;
import org.bukkit.entity.Player;

@CommandAlias("chestreset|resetchests")
@CommandPermission("skywars.admin")
public class ChestResetCommand extends BaseCommand {

    private ChestManager chestManager;

    @Default
    public void chestReset(Player user) {

        chestManager.resetChests();
        user.sendMessage(CC.translate("&aYou have reset the chests!"));

    }

}
