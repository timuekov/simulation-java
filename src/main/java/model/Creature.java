package model;

public abstract class Creature extends Entity {
    private int hp;
    private final int speed;

    public Creature(Coordinates coordinates, int hp, int speed) {
        super(coordinates);
        this.hp = hp;
        this.speed = speed;
    }

    public abstract void makeMove(SimulationMap map);

    protected void moveTo(Coordinates destination, SimulationMap map) {
        map.removeEntity(getCoordinates());
        setCoordinates(destination);
        map.setEntity(destination, this);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

}



