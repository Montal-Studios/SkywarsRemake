package net.montal.skywarsremake;

import lombok.Getter;
import net.montal.skywarsremake.manager.GameManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class SkywarsRemake extends JavaPlugin {

    private SkywarsRemake instance;
    private GameManager gameManager;

    @Override
    public void onEnable() {
        getLogger().info("Enabled");

        this.gameManager = new GameManager(this);

    }

    @Override
    public void onDisable() {

        gameManager.cleanup();

    }
}
