package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationMap {

    private final int width;
    private final int height;

    private final Map<Coordinates, Entity> entities = new HashMap<>();

    public SimulationMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public <T extends Entity> List<T> getEntitiesBy(Class<T> type) {
        List<T> matchingEntities = new ArrayList<>();

        for (Entity entity : entities.values()) {
            if (type.isInstance(entity)) {
                matchingEntities.add(type.cast(entity));
            }
        }

        return matchingEntities;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setEntity(Coordinates coordinates, Entity entity) {
        if (isCellEmpty(coordinates)) {
            entities.put(coordinates, entity);
        }
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public Entity removeEntity(Coordinates coordinates) {
        return entities.remove(coordinates);
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        return (entities.get(coordinates) == null);

    }

    public List<Coordinates> getGrassCoordinates() {
        List<Coordinates> grassCoords = new ArrayList<>();

        for (Entity entity : entities.values()) {
            if (entity instanceof Grass grass) {
                grassCoords.add(grass.getCoordinates());
            }
        }
        return grassCoords;
    }

    public List<Coordinates> getHerbivoreCoordinates() {
        List<Coordinates> herbivoreCoords = new ArrayList<>();

        for (Entity entity : entities.values()) {
            if (entity instanceof Herbivore herbivore) {
                herbivoreCoords.add(herbivore.getCoordinates());
            }
        }
        return herbivoreCoords;
    }


    public int getOccupiedCellCount() {
        return entities.size();

    }
}

