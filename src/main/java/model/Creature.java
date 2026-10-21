package model;

import pathfinding.PathFinder;

import java.util.List;

public abstract class Creature extends Entity {
    private int hp;
    private final int speed;

    private final Class<? extends Entity> food;

    public Creature(Coordinates coordinates, int hp, int speed, Class<? extends Entity> food) {
        super(coordinates);
        this.hp = hp;
        this.speed = speed;
        this.food = food;
    }

    protected List<Coordinates> findPath(SimulationMap map) {

        return PathFinder.find(getCoordinates(), food, map);
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



