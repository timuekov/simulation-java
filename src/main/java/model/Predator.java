package model;

import pathfinding.PathFinder;

import java.util.List;
import java.util.Optional;

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

        List<Coordinates> path = PathFinder.find(getCoordinates(), Herbivore.class, map);
        if (path.isEmpty()) {
            return;
        }

        Coordinates nearestHerbivore;
        nearestHerbivore = path.get(path.size() - 1);

        if (path.size() == 2) {
            Optional<Entity> herb = map.getEntity(nearestHerbivore);
            if (herb.isPresent()) {
                if (herb.get() instanceof Herbivore herbivore) {
                    herbivore.takeDamage(this.attackPower);
                    if (herbivore.getHp() <= 0) {
                        map.removeEntity(nearestHerbivore);
                    }
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
