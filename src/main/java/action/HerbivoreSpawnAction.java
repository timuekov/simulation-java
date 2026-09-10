package action;

import model.Coordinates;
import model.Entity;
import model.Herbivore;

public class HerbivoreSpawnAction extends SpawnAction {

    private final int hp;
    private final int speed;

    public HerbivoreSpawnAction(int count, int hp, int speed) {
        super(count); // count уходит наверх, в SpawnAction
        this.hp = hp;
        this.speed = speed;
    }

    @Override
    protected Entity createEntity(Coordinates coordinates) {
        return new Herbivore(coordinates, hp, speed);
    }
}

