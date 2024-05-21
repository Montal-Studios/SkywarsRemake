package net.montal.skywarsremake.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.montal.skywarsremake.object.GamePlayer;
import net.montal.skywarsremake.object.GameState;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkywarsGame {
    private List<GamePlayer> players;
    private GameState currentState;
}