package model;

import pathfinding.PathFinder;

import java.util.List;

public class Predator extends Creature {
    private final int attackPower;

    public Predator(Coordinates coordinates, int hp, int speed, int attackPower) {
        super(coordinates, hp, speed);
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public void makeMove(SimulationMap map) {
        List<Coordinates> allHerbivore = map.getHerbivoreCoordinates();
        Coordinates nearestHerbivore = findNearestTarget(allHerbivore);
        if (nearestHerbivore == null) {
            return;
        }

        List<Coordinates> path = PathFinder.findPath(getCoordinates(), nearestHerbivore, map);
        if (path == null) {
            return;
        }

        if (path.size() == 2) {
            Entity herb = map.getEntity(nearestHerbivore);
            if (herb instanceof Herbivore herbivore) {
                herbivore.takeDamage(this.attackPower);
                if (herbivore.getHp() <= 0) {
                    map.removeEntity(nearestHerbivore);
                }
            }
            return;
        }


        int safeIndex = Math.min(getSpeed(), path.size() - 2);

        Coordinates destination = path.get(safeIndex);

        map.removeEntity(getCoordinates());
        setCoordinates(destination);
        map.setEntity(destination, this);
    }

}
