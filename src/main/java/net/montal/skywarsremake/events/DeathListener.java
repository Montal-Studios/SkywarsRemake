package net.montal.skywarsremake.events;

import net.montal.skywarsremake.manager.GameManager;
import net.montal.skywarsremake.manager.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.checkerframework.common.initializedfields.qual.EnsuresInitializedFields;

public class DeathListener implements Listener {

    private GameManager gameManager;

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        Player user = e.getEntity();

    }

}
