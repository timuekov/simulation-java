package action;

import model.Coordinates;
import model.Entity;
import model.Rock;

public class RockSpawnAction extends SpawnAction {

    public RockSpawnAction(int count) {
        super(count);
    }

    @Override
    protected Entity createEntity(Coordinates coordinates) {

        return new Rock();
    }
}

