package net.montal.skywarsremake.events;

import net.montal.skywarsremake.manager.GameManager;
import net.montal.skywarsremake.manager.SkywarsGame;
import net.montal.skywarsremake.object.GamePlayer;
import net.montal.skywarsremake.object.GameState;
import net.montal.skywarsremake.object.GameStateManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    private GameManager gameManager;
    private GameStateManager gameStateManager;
    private GamePlayer gamePlayer;

    @EventHandler
    public void onDamage(EntityDamageEvent e) {

        if (e.getEntity() instanceof Player) {

            Player p = (Player) e.getEntity();

            SkywarsGame game = GameManager.getGameForPlayer(gamePlayer.getPlayer());
            if (game != null) {
                if (GameManager.isState(GameState.PREGAME) || GameManager.isState(GameState.ENDED)) {
                    e.setCancelled(true);
                }
            }

        }

    }

}
