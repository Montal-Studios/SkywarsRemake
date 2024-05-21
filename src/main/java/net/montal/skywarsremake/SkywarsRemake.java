package net.montal.skywarsremake;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.montal.skywarsremake.command.ChestResetCommand;
import net.montal.skywarsremake.command.MainCommand;
import net.montal.skywarsremake.events.DamageListener;
import net.montal.skywarsremake.events.DeathListener;
import net.montal.skywarsremake.events.FoodLevelListener;
import net.montal.skywarsremake.manager.chests.ChestManager;
import net.montal.skywarsremake.object.GameStateManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@SuppressWarnings("LombokGetterMayBeUsed")
public final class SkywarsRemake extends JavaPlugin {

    private static SkywarsRemake instance;
    private GameStateManager gameStateManager;
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

        this.gameStateManager = new GameStateManager(this);

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
        pm.registerEvents(new DeathListener(), this);
        pm.registerEvents(new DamageListener(), this);
        pm.registerEvents(new FoodLevelListener(), this);

    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled.");

        gameStateManager.cleanup();

    }
}
