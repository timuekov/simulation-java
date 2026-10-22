package action;

import model.Coordinates;
import model.Entity;
import model.SimulationMap;

import java.util.Random;
import java.util.function.Function;

public class SpawnAction extends Action {

    private final int count;
    private final Function<Coordinates, Entity> entityCreator;

    public SpawnAction(int count, Function<Coordinates, Entity> entityCreator) {

        this.count = count;
        this.entityCreator = entityCreator;
    }

    public void perform(SimulationMap map) {
        for (int i = 0; i < count; i++) {
            if (map.getOccupiedCellCount() == map.getWidth() * map.getHeight()) {
                return;
            }

            Coordinates coordinates = getRandomEmptyCoordinates(map);
            Entity entity = entityCreator.apply(coordinates);
            map.setEntity(coordinates, entity);
        }
    }

    private Coordinates getRandomEmptyCoordinates(SimulationMap map) {
        Random random = new Random();
        Coordinates coordinates;


        do {
            int x = random.nextInt(map.getWidth());
            int y = random.nextInt(map.getHeight());
            coordinates = new Coordinates(x, y);

        } while (!map.isCellEmpty(coordinates));
        return coordinates;

    }
}
