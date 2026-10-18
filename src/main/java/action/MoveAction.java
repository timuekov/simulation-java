package action;

import model.Creature;
import model.SimulationMap;

import java.util.List;

public class MoveAction extends Action {
    @Override
    public void perform(SimulationMap map) {
        List<Creature> creatures = map.getEntitiesBy(Creature.class);

        for (Creature creature: creatures) {
            if (map.getEntity(creature.getCoordinates()) == creature) {
                creature.makeMove(map);
            }
        }
    }
}
