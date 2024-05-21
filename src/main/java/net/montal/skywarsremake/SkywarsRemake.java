package net.montal.skywarsremake;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.montal.skywarsremake.command.ChestResetCommand;
import net.montal.skywarsremake.command.MainCommand;
import net.montal.skywarsremake.manager.chests.ChestManager;
import net.montal.skywarsremake.object.GameStateManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@SuppressWarnings("LombokGetterMayBeUsed")
public final class SkywarsRemake extends JavaPlugin {

    private static SkywarsRemake instance;
    private GameStateManager gameManager;
    private ChestManager chestManager;

    private PaperCommandManager manager;

    public static SkywarsRemake getInstance() {
        return instance;
    }

    public PaperCommandManager getCommandManager() {
        return manager;
    }

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("Enabled");

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        chestManager = new ChestManager(getConfig());

        this.gameManager = new GameStateManager(this);

        registerCommands();
        registerListeners();

    }

    private void registerCommands() {
        manager = new PaperCommandManager(this);

        manager.registerCommand(new ChestResetCommand());
        manager.registerCommand(new MainCommand());

    }

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(chestManager, this);

    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled.");

        gameManager.cleanup();

    }
}
