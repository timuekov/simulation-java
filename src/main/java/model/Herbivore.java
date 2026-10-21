package model;

import java.util.List;

public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates, int hp, int speed) {
        super(coordinates, hp, speed, Grass.class);
    }

    @Override
    public void makeMove(SimulationMap map) {

        List<Coordinates> path = findPath(map);
        if (path.isEmpty()) {
            return;
        }

        Coordinates nearestGrass = path.get(path.size() - 1);

        int safeIndex = Math.min(getSpeed(), path.size() - 1);
        Coordinates destination = path.get(safeIndex);

        if (destination.equals(nearestGrass)) {
            map.removeEntity(nearestGrass);
            moveTo(nearestGrass, map);
        } else {
            moveTo(destination, map);

        }


    }

    public void takeDamage(int damageValue) {
        setHp(getHp() - damageValue);
    }
}
