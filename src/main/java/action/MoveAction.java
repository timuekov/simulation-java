package action;

import model.Creature;
import model.SimulationMap;

import java.util.List;

public class MoveAction extends Action {
    @Override
    public void perform(SimulationMap map) {
        List<Creature> creatures = map.getAllCreatures();

        for (Creature creature: creatures) {
            if (map.getEntity(creature.getCoordinates()) == creature) {
                creature.makeMove(map);
            }
        }
    }
}
