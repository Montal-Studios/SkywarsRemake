package net.montal.skywarsremake.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.montal.skywarsremake.manager.Kit;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamePlayer {
    private UUID uuid;
    private Kit selectedKit;
}
