package action;

import model.Coordinates;
import model.Entity;
import model.Tree;

public class TreeSpawnAction extends SpawnAction {

    public TreeSpawnAction(int count) {
        super(count);
    }

    @Override
    protected Entity createEntity(Coordinates coordinates) {

        return new Tree();
    }
}

