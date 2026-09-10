package action;


import model.Coordinates;
import model.Entity;
import model.Predator;

public class PredatorSpawnAction extends SpawnAction {

    private final int hp;
    private final int speed;
    private final int attackPower;

    public PredatorSpawnAction(int count, int hp, int speed, int attackPower) {
        super(count);
        this.hp = hp;
        this.speed = speed;
        this.attackPower = attackPower;
    }

    @Override
    protected Entity createEntity(Coordinates coordinates) {
        return new Predator(coordinates, hp, speed, attackPower);
    }
}

