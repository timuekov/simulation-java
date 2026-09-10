package action;

import model.SimulationMap;

public class MaintainGrassAction extends Action{
    private  final int minimumGrass;


    @Override
    public void perform(SimulationMap map){
        int currentGrass = map.getGrassCoordinates().size();

        if (currentGrass < minimumGrass) {
            int grassToSpawn = minimumGrass - currentGrass;

            GrassSpawnAction grassSpawnAction = new GrassSpawnAction(grassToSpawn);
            grassSpawnAction.perform(map);

        }

    }

    public MaintainGrassAction(int minimumGrass){
        this.minimumGrass = minimumGrass;


    }
}

