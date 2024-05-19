package net.montal.skywarsremake.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamePlayer {
    private UUID uuid;
    private Kit selectedKit;
}
