package model;

import pathfinding.PathFinder;

import java.util.List;

public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates, int hp, int speed) {
        super(coordinates, hp, speed);
    }

    @Override
    public void makeMove(SimulationMap map) {

        List<Coordinates> path = PathFinder.find(getCoordinates(), Grass.class, map);
        if (path.isEmpty()) {
            return;
        }

        Coordinates nearestGrass;
        nearestGrass = path.get(path.size() - 1);


        int safeIndex = Math.min(getSpeed(), path.size() - 1);
        Coordinates destination = path.get(safeIndex);

        if (destination.equals(nearestGrass)) {
            map.removeEntity(nearestGrass);
            map.removeEntity(getCoordinates());
            setCoordinates(nearestGrass);
            map.setEntity(nearestGrass, this);
        } else {
            map.removeEntity(getCoordinates());
            setCoordinates(destination);
            map.setEntity(destination, this);

        }


    }

    public void takeDamage(int damageValue) {
        setHp(getHp() - damageValue);
    }
}
