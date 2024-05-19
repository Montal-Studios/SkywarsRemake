package net.montal.skywarsremake.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkywarsGame {
    private SkywarsServer server;
    private List<GamePlayer> players;
}