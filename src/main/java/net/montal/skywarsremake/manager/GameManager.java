package net.montal.skywarsremake.manager;

import lombok.experimental.UtilityClass;
import net.montal.skywarsremake.object.GamePlayer;
import net.montal.skywarsremake.utils.TaskManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Store the active games being played on the server
 */
@UtilityClass
public class GameManager {
    private final List<SkywarsGame> games = Arrays.asList(new SkywarsGame[12]); // Fixed amount of 12 instances per server

    public boolean createGame() {
        if (atMaxCapacity()) return false;

        SkywarsGame skywarsGame = new SkywarsGame();

        return true;
    }

    public SkywarsGame getGameForPlayer(Player player) {
        UUID targetUUID = player.getUniqueId();

        Callable<SkywarsGame> task = () -> {
            Optional<GamePlayer> foundGamePlayer = games.stream()
                    .flatMap(server -> server.getPlayers().stream())
                    .filter(gamePlayer -> gamePlayer.getUuid().equals(targetUUID))
                    .findFirst();

            if (foundGamePlayer.isEmpty()) {
                return null;
            }

            Optional<SkywarsGame> foundGame = games.stream()
                    .filter(server -> server.getPlayers().contains(foundGamePlayer.get()))
                    .findFirst();

            return foundGame.orElse(null);
        };

        return TaskManager.submit(task);
    }

    public boolean atMaxCapacity() {
        return games.size() == 12;
    }

    public boolean atMinCapacity() {
        return games.size() == 2;
    }

}
