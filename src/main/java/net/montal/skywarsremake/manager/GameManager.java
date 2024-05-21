package net.montal.skywarsremake.manager;

import lombok.experimental.UtilityClass;
import net.montal.skywarsremake.SkywarsRemake;
import net.montal.skywarsremake.object.GamePlayer;
import net.montal.skywarsremake.object.GameState;
import net.montal.skywarsremake.object.GameStateManager;
import net.montal.skywarsremake.tasks.GameStartCooldownTask;
import net.montal.skywarsremake.utils.CC;
import net.montal.skywarsremake.utils.TaskManager;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.Callable;

/**
 * Store the active games being played on the server
 */
@UtilityClass
public class GameManager {

    private String displayName;
    private int maxPlayers;
    private int minPlayers;
    private World world;
    private List<Location> spawnPoint;

    private boolean isTeamGame;
    private GameStateManager gameStateManager;
    private GameState gameState;
    private List<GamePlayer> players;
    private Set<GamePlayer> spectators;
    private Set<Chest> opened;
    private boolean movementFrozen = false;

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

    public void activateSpectatorSettings(Player player) {
        GamePlayer gamePlayer = getGamePlayer(player);

        player.setMaxHealth(20);
        player.setHealth(player.getMaxHealth());
        player.setGameMode(GameMode.SPECTATOR);

        if (gamePlayer != null ) {
            switchToSpectator(gamePlayer);
        }

    }

    public boolean atMaxCapacity() {
        return games.size() == 12;
    }

    public boolean isState(GameState state) {
        return getGameState() == state;
    }

    public void setState(GameState gameState) {
        gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public List<GamePlayer> getPlayers() {
        return players;
    }

    public Set<GamePlayer> getSpectators() {
        return spectators;
    }

    public GamePlayer getGamePlayer(Player player) {
        for (GamePlayer gamePlayer : getPlayers()) {
            if (gamePlayer.isTeamClass()) {
                //
            } else {
                if (gamePlayer.getPlayer() == player) {
                    return gamePlayer;
                }
            }
        }

        for (GamePlayer gamePlayer : getSpectators()) {
            if (gamePlayer.isTeamClass()) {
                //
            } else {
                if (gamePlayer.getPlayer() == player) {
                    return gamePlayer;
                }
            }
        }

        return null;

    }

    public void sendMessage(String message) {
        for (GamePlayer gamePlayer : getPlayers()) {
            gamePlayer.sendMessage(message);
        }
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public boolean joinGame(GamePlayer gamePlayer) {

        if (gamePlayer.isTeamClass() && isState(GameState.STARTING)) {
            return false;
        }

        if (isState(GameState.STARTING)) {
            if (getPlayers().size() == getMaxPlayers()) {
                gamePlayer.sendMessage(CC.translate("&cThis game is full!"));
                return false;
            }
        }

        getPlayers().add(gamePlayer);

        if (getPlayers().size() == getMinPlayers() && !isState(GameState.STARTING)) {
            setState(GameState.STARTING);
            sendMessage(CC.translate("&aThe game will begin in 30 seconds!"));
            startCooldown();
        }

        return true;

    }

    public void startCooldown() {
        new GameStartCooldownTask(SkywarsRemake.getInstance().getGameStateManager()).startCooldown();
    }

    public boolean isTeamGame() {
        return isTeamGame;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void switchToSpectator(GamePlayer gamePlayer) {
        getPlayers().remove(gamePlayer);
        getSpectators().add(gamePlayer);
    }

    public Set<Chest> getOpened() {
        return opened;
    }

    public void setMovementFrozen() {
        movementFrozen = movementFrozen;
    }

    public boolean isMovementFrozen() {
        return movementFrozen;
    }

    public World getWorld() {
        return world;
    }

    public boolean atMinCapacity() {
        return games.size() == 2;
    }

}