package net.montal.skywarsremake.object;

import lombok.Getter;
import net.montal.skywarsremake.SkywarsRemake;
import net.montal.skywarsremake.manager.SkywarsGame;
import net.montal.skywarsremake.tasks.GameStartCooldownTask;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class GameStateManager {

    private final SkywarsRemake instance;
    public GameState gameState = GameState.PREGAME;
    private SkywarsGame skywarsGame;

    @Getter
    //private final PlayerManager playerManager;
    private GameStartCooldownTask gameStartCooldownTask;

    public GameStateManager(SkywarsRemake instance) {
        this.instance = instance;
        this.gameStartCooldownTask = gameStartCooldownTask;

        //this.playerManager = new PlayerManager(this);
    }

    public void setGameState(GameState gameState) {
        if (this.gameState == GameState.ACTIVE && gameState == GameState.STARTING) return;

        this.gameState = gameState;
        /*switch (gameState){
            case STARTING:
                Bukkit.broadcastMessage(CC.translate("&aGame starting!"));
                this.gameStartCooldownTask = new GameStartCooldownTask(this);
                this.gameStartCooldownTask.runTaskTimer(instance, 0, 20);
                break;
            case ACTIVE:
                this.gameStartCooldownTask.cancel(); //i
                getPlayerManager().giveKits();
                Bukkit.broadcastMessage(CC.translate("&aGame started!"));
                break;
            case ENDED:
                Bukkit.broadcastMessage(CC.translate("&aGame ended!"));
                break;
        }*/
    }

    public void cleanup() {

    }

    public List<GamePlayer> getGamePlayers() {
        return this.skywarsGame.getPlayers();
    }

    public List<Player> getBukkitPlayers() {
        return this.skywarsGame.getPlayers().stream()
                .map(gamePlayer -> Bukkit.getPlayer(gamePlayer.getUuid()))
                .filter(Objects::nonNull)
                .toList();
    }

}
