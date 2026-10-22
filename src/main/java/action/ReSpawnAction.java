package action;

import model.Entity;
import model.SimulationMap;

import java.util.function.IntFunction;

public class ReSpawnAction extends Action {
    private final int minimum;
    private final Class<? extends Entity> type;
    private final IntFunction<SpawnAction> spawnFactory;

    public ReSpawnAction(int minimum, Class<? extends Entity> type, IntFunction<SpawnAction> spawnFactory) {
        this.minimum = minimum;
        this.type = type;
        this.spawnFactory = spawnFactory;
    }

    @Override
    public void perform(SimulationMap map) {
        int currentCount = map.getEntitiesBy(type).size();

        if (currentCount < minimum) {
            int countToSpawn = minimum - currentCount;
            SpawnAction spawnAction = spawnFactory.apply(countToSpawn);
            spawnAction.perform(map);
        }
    }
}
