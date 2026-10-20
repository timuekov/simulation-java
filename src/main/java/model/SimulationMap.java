package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        } else {
            throw new IllegalStateException("Координаты " + coordinates + " уже заняты!");
        }
    }

    public Optional<Entity> getEntity(Coordinates coordinates) {
        validate(coordinates);
        return Optional.ofNullable(entities.get(coordinates));
    }

    public void removeEntity(Coordinates coordinates) {
        validate(coordinates);
        entities.remove(coordinates);
    }

    private void validate(Coordinates coordinates) {
        if (coordinates.getX() < 0 || coordinates.getX() >= width || coordinates.getY() < 0 || coordinates.getY() >= height) {
            throw new IllegalArgumentException("Координаты " + coordinates + " вышли за пределы карты");
        }
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        validate(coordinates);
        return (entities.get(coordinates) == null);
    }


    public int getOccupiedCellCount() {
        return entities.size();

    }
}

