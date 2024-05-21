package net.montal.skywarsremake.events;

import net.montal.skywarsremake.manager.GameManager;
import net.montal.skywarsremake.manager.SkywarsGame;
import net.montal.skywarsremake.object.GamePlayer;
import net.montal.skywarsremake.object.GameState;
import net.montal.skywarsremake.object.GameStateManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelListener implements Listener {

    private GameManager gameManager;
    private GamePlayer gamePlayer;
    private GameStateManager gameStateManager;

    @EventHandler
    public void onFoodLevel(FoodLevelChangeEvent e) {

        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();

            SkywarsGame game = GameManager.getGameForPlayer(gamePlayer.getPlayer());
            if (game != null && GameManager.getGamePlayer(p) != null) {
                GamePlayer gamePlayer2 = GameManager.getGamePlayer(p);

                if (!(GameManager.isState(GameState.ACTIVE))) {
                    if (gamePlayer2.isTeamClass()) {
                        if (gamePlayer2.getTeam().isPlayer(p)) {
                            e.setFoodLevel(25);
                            e.setCancelled(true);
                        }

                    } else {
                        if (gamePlayer2.getPlayer() == p) {
                            e.setFoodLevel(25);
                            e.setCancelled(true);
                        }
                    }

                }

            }

        }

    }

}
