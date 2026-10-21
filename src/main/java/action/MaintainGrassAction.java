package action;

import model.Grass;
import model.SimulationMap;

public class MaintainGrassAction extends Action {
    private final int minimumGrass;

    public MaintainGrassAction(int minimumGrass) {
        this.minimumGrass = minimumGrass;
    }

    @Override
    public void perform(SimulationMap map) {
        int currentGrass = map.getEntitiesBy(Grass.class).size();

        if (currentGrass < minimumGrass) {
            int grassToSpawn = minimumGrass - currentGrass;

            GrassSpawnAction grassSpawnAction = new GrassSpawnAction(grassToSpawn);
            grassSpawnAction.perform(map);
        }
    }
}

