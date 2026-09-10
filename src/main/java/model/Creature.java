package model;

import java.util.List;

public abstract class Creature extends Entity {
    private int hp;
    private final int speed;

    public Creature(Coordinates coordinates, int hp, int speed) {
        super(coordinates);
        this.hp = hp;
        this.speed = speed;
    }

    public abstract void makeMove(SimulationMap map);

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }


    public Coordinates findNearestTarget(List<Coordinates> targets) {
        Coordinates nearest = null;
        int minDistance = Integer.MAX_VALUE;

        for (Coordinates targetCoords : targets) {
            int dx = Math.abs(getCoordinates().getX() - targetCoords.getX());
            int dy = Math.abs(getCoordinates().getY() - targetCoords.getY());
            int distance = dx + dy;

            if (distance < minDistance) {
                minDistance = distance;
                nearest = targetCoords;
            }
        }

        return nearest;

    }

}



