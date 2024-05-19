package net.montal.skywarsremake.tasks;

import net.montal.skywarsremake.manager.GameManager;
import net.montal.skywarsremake.manager.GameState;
import net.montal.skywarsremake.manager.PlayerManager;
import net.montal.skywarsremake.manager.SkywarsGame;
import net.montal.skywarsremake.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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


        if (timeLeft % 5 == 0 || timeLeft <= 5) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.sendTitle(CC.translate("&a" + timeLeft), CC.translate(""));
                Bukkit.broadcastMessage(CC.translate("&aThe game starts in &b" + timeLeft + " &aseconds!"));
            }
        }

    }

}
