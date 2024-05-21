package net.montal.skywarsremake.events;

import net.montal.skywarsremake.manager.GameManager;
import net.montal.skywarsremake.manager.SkywarsGame;
import net.montal.skywarsremake.object.GameState;
import net.montal.skywarsremake.object.GameStateManager;
import net.montal.skywarsremake.utils.CC;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    private GameManager gameManager;
    private GameStateManager gameStateManager;

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player user = e.getEntity();

        SkywarsGame game = GameManager.getGameForPlayer(user.getPlayer());
        if (GameManager.isState(GameState.ACTIVE)) {
            e.setDeathMessage(CC.translate("&a" + user.getName() + " &ahas died!"));
            user.setGameMode(GameMode.SPECTATOR);
            user.sendMessage(CC.translate("&aYou have died!"));
        }

    }

}
