package view;

import model.*;

/**
 * Класс, отвечающий за визуализацию (отрисовку) игрового поля в консоли.
 * Проходит по всей сетке карты и выводит графические символы (спрайты) сущностей.
 */
public class MapRenderer {

    /**
     * Отрисовывает текущее состояние карты в консоли.
     * Проход выполняется построчно: сверху вниз (y), слева направо (x).
     *
     * @param map карта симуляции, содержащая размеры и координаты всех сущностей
     */
    public void render(SimulationMap map) {
        // Проходим по строкам (высота карты)
        for (int y = 0; y < map.getHeight(); y++) {

            // Проходим по столбцам внутри текущей строки (ширина карты)
            for (int x = 0; x < map.getWidth(); x++) {
                Coordinates coordinates = new Coordinates(x, y);
                Entity entity = map.getEntity(coordinates).orElse(null);

                System.out.print(getEntitySprite(entity));
            }
            // В конце строки переносим курсор на новый ряд
            System.out.println();
        }
    }

    /**
     * Определяет строковый символ (эмодзи/спрайт) для переданной сущности
     * на основе её фактического типа.
     *
     * @param entity объект сущности, находящийся на клетке (может быть null)
     * @return строковое представление сущности для вывода в консоль
     */
    private String getEntitySprite(Entity entity) {
        if (entity == null) {
            return ". ";
        }
        if (entity instanceof Grass) {
            return "🌿";
        }
        if (entity instanceof Rock) {
            return "⛰";
        }
        if (entity instanceof Tree) {
            return "🌲";
        }
        if (entity instanceof Herbivore) {
            return "🐇";
        }
        if (entity instanceof Predator) {
            return "🐺";
        }
        // Запасной вариант на случай добавления новых сущностей
        return "? ";
    }
}