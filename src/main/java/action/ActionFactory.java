package action;

import model.Grass;
import model.Herbivore;
import model.Predator;
import model.Rock;
import model.Tree;

import java.util.List;

public class ActionFactory {
    public List<Action> createInitActions() {

        SpawnAction grassSpawnAction = new SpawnAction(2, coordinates -> new Grass());
        SpawnAction rockSpawnAction = new SpawnAction(3, coordinates -> new Rock());
        SpawnAction treeSpawnAction = new SpawnAction(3, coordinates -> new Tree());

        SpawnAction predatorSpawnAction = new SpawnAction(3, coordinates -> new Predator(coordinates, 5, 2, 4));
        SpawnAction herbivoreSpawnAction = new SpawnAction(5, coordinates -> new Herbivore(coordinates, 10, 3));

        return List.of(rockSpawnAction, treeSpawnAction, predatorSpawnAction, herbivoreSpawnAction, grassSpawnAction);
    }

    public List<Action> createTurnActions() {
        MoveAction moveAction = new MoveAction();

        ReSpawnAction grassReSpawnAction = new ReSpawnAction(2, Grass.class, count -> new SpawnAction(count, coordinates -> new Grass()));
        ReSpawnAction herbivoreReSpawnAction = new ReSpawnAction(2, Herbivore.class, count -> new SpawnAction(count, coordinates -> new Herbivore(coordinates, 10, 3)));

        return List.of(moveAction, grassReSpawnAction, herbivoreReSpawnAction);
    }
}
