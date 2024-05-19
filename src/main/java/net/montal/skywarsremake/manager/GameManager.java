package net.montal.skywarsremake.manager;

import lombok.Getter;
import net.montal.skywarsremake.SkywarsRemake;
import net.montal.skywarsremake.tasks.GameStartCooldownTask;
import net.montal.skywarsremake.utils.CC;
import org.bukkit.Bukkit;

public class GameManager {

    private final SkywarsRemake instance;
    public GameState gameState = GameState.PREGAME;

    @Getter
    private final PlayerManager playerManager;
    private GameStartCooldownTask gameStartCooldownTask;

    public GameManager(SkywarsRemake instance) {
        this.instance = instance;
        this.gameStartCooldownTask = gameStartCooldownTask;

        this.playerManager = new PlayerManager(this);
    }

    public void setGameState(GameState gameState) {
        if (this.gameState == GameState.ACTIVE && gameState == GameState.STARTING) return;

        this.gameState = gameState;
        switch (gameState){
            case STARTING:
                Bukkit.broadcastMessage(CC.translate("&aGame starting!"));
                this.gameStartCooldownTask = new GameStartCooldownTask(this);
                this.gameStartCooldownTask.runTaskTimer(instance, 0, 20);
                break;
            case ACTIVE:
                if (this.gameState !=  null) this.gameStartCooldownTask.cancel(); //i
                getPlayerManager().giveKits();
                Bukkit.broadcastMessage(CC.translate("&aGame started!"));
                break;
            case ENDED:
                Bukkit.broadcastMessage(CC.translate("&aGame ended!"));
                break;
        }
    }

    public void cleanup() {

    }

}
