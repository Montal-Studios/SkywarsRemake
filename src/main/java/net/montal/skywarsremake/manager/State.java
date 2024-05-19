package net.montal.skywarsremake.manager;

import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashSet;
import java.util.Set;

@Getter
public abstract class State {
    private SkywarsGame game;

    protected final Set<Listener> listeners = new HashSet<>();
    protected final Set<BukkitTask> tasks = new HashSet<>();

    public abstract void onStart();
    public abstract void onEnd();
    public abstract boolean readyToEnd();
}
