package action;

import model.Coordinates;
import model.Entity;
import model.Grass;

public class GrassSpawnAction extends SpawnAction {

    public GrassSpawnAction(int count) {
        super(count); // передаём count наверх, в конструктор SpawnAction
    }

    @Override
    protected Entity createEntity(Coordinates coordinates) {

        return new Grass();
    }
}

