package net.montal.skywarsremake.tasks;

import net.montal.skywarsremake.SkywarsRemake;
import net.montal.skywarsremake.object.GameState;
import net.montal.skywarsremake.object.GameStateManager;
import net.montal.skywarsremake.utils.CC;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class GameStartCooldownTask {

    private GameStateManager gameManager;

    public GameStartCooldownTask(GameStateManager gameManager){
        this.gameManager = gameManager;
    }

    public void startCooldown() {
        new BukkitRunnable() {

            int timer = 30;

            @Override
            public void run() {

                if (timer > 0) {
                    if (timer == 30) {
                        gameManager.setGameState(GameState.STARTING);
                        for (Player all : gameManager.getBukkitPlayers()) {
                            all.sendMessage(CC.translate("&eThe game starts in &b30 &eseconds!"));
                        }
                    }
                    if (timer == 20) {
                        for (Player all : gameManager.getBukkitPlayers()) {
                            all.sendMessage(CC.translate("&eThe game starts in &b20 &eseconds!"));
                        }
                    }
                    if (timer == 10) {
                        for (Player all : gameManager.getBukkitPlayers()) {
                            all.sendMessage(CC.translate("&eThe game starts in &b10 &eseconds!"));
                            all.sendTitle(CC.translate("&c10"), CC.translate("&ePrepare to fight!"));
                        }
                    }
                    if (timer == 5) {
                        for (Player all : gameManager.getBukkitPlayers()) {
                            all.sendMessage(CC.translate("&eThe game starts in &b50 &eseconds!"));
                            all.sendTitle(CC.translate("&c5"), CC.translate("&ePrepare to fight!"));
                        }
                    }
                    if (timer == 4) {
                        for (Player all : gameManager.getBukkitPlayers()) {
                            all.sendMessage(CC.translate("&eThe game starts in &b50 &eseconds!"));
                            all.sendTitle(CC.translate("&c4"), CC.translate("&ePrepare to fight!"));
                        }
                    }
                    if (timer == 3) {
                        for (Player all : gameManager.getBukkitPlayers()) {
                            all.sendMessage(CC.translate("&eThe game starts in &b50 &eseconds!"));
                            all.sendTitle(CC.translate("&c3"), CC.translate("&ePrepare to fight!"));
                        }
                    }
                    if (timer == 2) {
                        for (Player all : gameManager.getBukkitPlayers()) {
                            all.sendMessage(CC.translate("&eThe game starts in &b50 &eseconds!"));
                            all.sendTitle(CC.translate("&c2"), CC.translate("&ePrepare to fight!"));
                        }
                    }
                    if (timer == 1) {
                        for (Player all : gameManager.getBukkitPlayers()) {
                            all.sendMessage(CC.translate("&eThe game starts in &b50 &eseconds!"));
                            all.sendTitle(CC.translate("&c1"), CC.translate("&ePrepare to fight!"));
                            all.getInventory().addItem(new ItemStack(Material.WOODEN_SWORD, 1));
                        }
                    }
                    timer--;
                } else {
                    for (Player all : gameManager.getBukkitPlayers()) {
                        all.sendMessage(CC.translate("&aThe game has begun!"));
                        Location loc = all.getLocation();
                        all.playSound(loc, Sound.ENTITY_PLAYER_LEVELUP, 5F, 1F);
                    }
                    cancel();
                }

            }
        }.runTaskTimer(SkywarsRemake.getInstance(), 20L, 20L);

    }

}
