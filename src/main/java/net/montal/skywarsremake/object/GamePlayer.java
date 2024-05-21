package net.montal.skywarsremake.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.montal.skywarsremake.manager.Kit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamePlayer {

    private Player player = null;
    private GameTeam team = null;
    private GamePlayerState gamePlayerState;
    private Location spawnPoint;

    public GamePlayer(Player player) {
        this.player = player;
    }

    private UUID uuid;
    private Kit selectedKit;

    public Player getPlayer() {
        return player;
    }

    public boolean isTeamClass() {
        return team != null && player == null;
    }

    public GameTeam getTeam() {
        return team;
    }

    public void sendMessage(String message) {
        if (isTeamClass()) {
            getTeam().sendMessage(message);
        }
    }

    public void teleport(Location location) {
        if (location == null) {
            return;
        }

        if (!isTeamClass()) {
            getPlayer().teleport(location);
        } else {
            getTeam().teleport(location);
        }

    }

    public String getName() {
        if (isTeamClass()) {
            return getTeam().getName();
         } else {
            return player.getDisplayName();
        }
    }

    public enum GamePlayerState {

    }

}
