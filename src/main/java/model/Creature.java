package model;

import pathfinding.PathFinder;

import java.util.List;

public abstract class Creature extends Entity {
    private int hp;
    private final int speed;

    private Coordinates coordinates;

    private final Class<? extends Entity> food;

    public Creature(Coordinates coordinates, int hp, int speed, Class<? extends Entity> food) {
        this.hp = hp;
        this.speed = speed;
        this.food = food;
        this.coordinates = coordinates;
    }

    protected List<Coordinates> findPath(SimulationMap map) {

        return PathFinder.find(coordinates, food, map);
    }

    public abstract void makeMove(SimulationMap map);

    protected void moveTo(Coordinates destination, SimulationMap map) {
        map.removeEntity(coordinates);
        this.coordinates = destination;
        map.setEntity(destination, this);
    }

    public Coordinates getCoordinates() {

        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {

        this.coordinates = coordinates;
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



