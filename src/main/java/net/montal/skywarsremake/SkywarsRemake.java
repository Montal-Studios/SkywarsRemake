package net.montal.skywarsremake;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.montal.skywarsremake.command.ChestResetCommannd;
import net.montal.skywarsremake.manager.GameManager;
import net.montal.skywarsremake.manager.chests.ChestManager;
import net.montal.skywarsremake.manager.chests.LootItem;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class SkywarsRemake extends JavaPlugin {

    private SkywarsRemake instance;
    private GameManager gameManager;
    private ChestManager chestManager;

    private PaperCommandManager manager;

    @Override
    public void onEnable() {
        getLogger().info("Enabled");
        manager = new PaperCommandManager(this);
        PluginManager pm = Bukkit.getServer().getPluginManager();

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        chestManager = new ChestManager(getConfig());

        this.gameManager = new GameManager(this);

        manager.registerCommand(new ChestResetCommannd());
        pm.registerEvents(chestManager, this);

    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled.");

        gameManager.cleanup();

    }
}
