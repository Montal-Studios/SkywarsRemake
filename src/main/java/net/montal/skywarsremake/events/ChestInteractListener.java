package net.montal.skywarsremake.events;

import net.montal.skywarsremake.manager.GameManager;
import net.montal.skywarsremake.manager.SkywarsGame;
import net.montal.skywarsremake.object.GamePlayer;
import net.montal.skywarsremake.object.GameState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ChestInteractListener implements Listener {

    @EventHandler
    public void onChest(PlayerInteractEvent e) {

        Player p = e.getPlayer();

        SkywarsGame game = GameManager.getGameForPlayer(p);
        if (game != null && GameManager.getGamePlayer(p) != null) {
            if (GameManager.isState(GameState.PREGAME)) {
                e.setCancelled(true);
                return;
            }
        }

        GamePlayer gamePlayer = GameManager.getGamePlayer(p);

        if (gamePlayer.isTeamClass()) {
            if (gamePlayer.getTeam().isPlayer(p)) {

            }
        }
    }



    public void handle(PlayerInteractEvent e, GameManager gameManager) {

        if (e.hasBlock() && e.getClickedBlock() != null && e.getClickedBlock().getState() instanceof Chest) {
            Chest chest = (Chest) e.getClickedBlock().getState();

        }

    }

}
