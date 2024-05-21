package net.montal.skywarsremake.events;

import net.montal.skywarsremake.manager.GameManager;
import net.montal.skywarsremake.manager.SkywarsGame;
import net.montal.skywarsremake.object.GamePlayer;
import net.montal.skywarsremake.object.GameState;
import net.montal.skywarsremake.object.GameStateManager;
import net.montal.skywarsremake.object.GameTeam;
import net.montal.skywarsremake.utils.CC;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    private GameManager gameManager;
    private GameStateManager gameStateManager;
    private GamePlayer gamePlayer;

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player user = e.getEntity();

        SkywarsGame game = GameManager.getGameForPlayer(gamePlayer.getPlayer());
        if (game != null && gamePlayer.getPlayer() != null) {
            if (GameManager.isState(GameState.ACTIVE)) {
                e.setDeathMessage(CC.translate("&a" + user.getName() + " &ahas died!"));
                user.setGameMode(GameMode.SPECTATOR);
                user.sendMessage(CC.translate("&aYou have died!"));

                if (gamePlayer.isTeamClass()) {
                    if (gamePlayer.getTeam().isPlayer(user)) {

                    }
                }
            }
        }
    }

    public void handle(Player player, PlayerDeathEvent e, GameManager gameManager) {

        if (!GameManager.isState(GameState.ACTIVE)) {
            return;
        }

        e.setDeathMessage(CC.translate("&a" + player.getName() + " &ahas died!"));
        GameManager.activateSpectatorSettings(player);

        if (GameManager.getPlayers().size() <= 1) {
            try {
                GamePlayer winner = GameManager.getPlayers().get(0);
                if (winner.isTeamClass()) {
                    GameManager.sendMessage("&a" + winner.getName() + " &ahas won!");
                }

                GameManager.setState(GameState.ENDED);
            } catch (IndexOutOfBoundsException ignored) {}
        }
    }
}
