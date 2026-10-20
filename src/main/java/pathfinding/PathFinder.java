package pathfinding;

import model.Coordinates;
import model.Entity;
import model.SimulationMap;

import java.util.*;

public class PathFinder {
    public static <T extends Entity> List<Coordinates> findPath(Coordinates start, Class<T> target, SimulationMap map) {

        Queue<Coordinates> queue = new LinkedList<>();
        Set<Coordinates> visited = new HashSet<>();
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>();

        Coordinates foundTarget = null;

        queue.add(start);
        visited.add(start);

        int[][] directions = {
                {0, -1},
                {0, 1},
                {-1, 0},
                {1, 0}
        };


        while (!queue.isEmpty() && foundTarget == null) {
            Coordinates current = queue.poll();


            for (int[] direction : directions) {
                int dx = direction[0];
                int dy = direction[1];

                Coordinates neighbor = new Coordinates(current.getX() + dx, current.getY() + dy);

                if (neighbor.getX() < 0 || neighbor.getX() >= map.getWidth() || neighbor.getY() < 0 || neighbor.getY() >= map.getHeight()) {
                    continue;
                }


                if (visited.contains(neighbor)) {
                    continue;
                }

                Optional<Entity> entity = map.getEntity(neighbor);

                if (entity.isPresent() && target.isInstance(entity.get())) {

                    foundTarget = neighbor;
                    cameFrom.put(neighbor, current);
                    visited.add(neighbor);
                    break;
                }

                if (!map.isCellEmpty(neighbor)) {
                    continue;
                }


                visited.add(neighbor);
                cameFrom.put(neighbor, current);
                queue.add(neighbor);

            }
        }


        if (foundTarget == null) {
            return new ArrayList<>();
        }

        List<Coordinates> path = new LinkedList<>();
        Coordinates step = foundTarget;

        while (step != null) {
            path.add(step);
            step = cameFrom.get(step);
        }

        Collections.reverse(path);

        return path;

    }
}





