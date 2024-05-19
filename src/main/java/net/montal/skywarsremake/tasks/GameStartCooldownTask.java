package net.montal.skywarsremake.tasks;

import net.montal.skywarsremake.manager.GameManager;
import net.montal.skywarsremake.manager.GameState;
import net.montal.skywarsremake.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class GameStartCooldownTask extends BukkitRunnable {

    private GameManager gameManager;

    public GameStartCooldownTask(GameManager gameManager){
        this.gameManager = gameManager;
    }

    private int timeLeft = 30;

    @Override
    public void run() {
        timeLeft--;
        if (timeLeft <= 0) {
            cancel();
            gameManager.setGameState(GameState.ACTIVE);
            return;
        }

        Bukkit.broadcastMessage(CC.translate("&a" + timeLeft + " seconds until the game starts!"));

    }

}
