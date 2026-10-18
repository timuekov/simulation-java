package action;

import model.Herbivore;
import model.SimulationMap;


public class MaintainHerbivoreAction extends Action {

    private final int minimumHerbivores;
    private final int hp;
    private final int speed;

    @Override
    public void perform(SimulationMap map) {
        int currentHerbivores = map.getEntitiesBy(Herbivore.class).size();

        if (currentHerbivores < minimumHerbivores) {
            int herbivoresToSpawn = minimumHerbivores - currentHerbivores;
            HerbivoreSpawnAction herbivoreSpawnAction = new HerbivoreSpawnAction(herbivoresToSpawn, hp, speed);
            herbivoreSpawnAction.perform(map);
        }
    }

    public MaintainHerbivoreAction(int minimumHerbivores, int hp, int speed) {
        this.minimumHerbivores = minimumHerbivores;
        this.hp = hp;
        this.speed = speed;
    }
}
