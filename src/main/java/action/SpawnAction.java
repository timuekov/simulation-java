package action;

import model.Coordinates;
import model.Entity;
import model.SimulationMap;

import java.util.Random;

public abstract class SpawnAction extends Action {

    private final int count;

    public SpawnAction(int count) {

        this.count = count;
    }

    protected abstract Entity createEntity(Coordinates coordinates);

    public void perform(SimulationMap map) {
        for (int i = 0; i < count; i++) {
            if (map.getOccupiedCellCount() == map.getWidth() * map.getHeight()) {
                return;
            }

            Coordinates coordinates = getRandomEmptyCoordinates(map);
            Entity entity = createEntity(coordinates);
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
