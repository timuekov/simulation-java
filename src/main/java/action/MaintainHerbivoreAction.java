package action;

import model.Creature;
import model.Herbivore;
import model.SimulationMap;

import java.util.List;

public class MaintainHerbivoreAction extends Action {

    private final int minimumHerbivores;
    private final int hp;
    private final int speed;

    @Override
    public void perform(SimulationMap map) {
        List<Creature> creatures = map.getAllCreatures();
        int currentHerbivores = 0;

        for (Creature creature : creatures) {
            if (creature instanceof Herbivore) {
                currentHerbivores += 1;
            }
        }
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
