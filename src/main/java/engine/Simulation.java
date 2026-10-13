package engine;

import action.Action;
import view.MapRenderer;
import model.SimulationMap;

import java.util.List;

public class Simulation {
    private final SimulationMap map;
    private final MapRenderer renderer;
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private boolean isRunning;
    private int turnCounter;
    private volatile boolean isPaused;


    public Simulation(SimulationMap map, MapRenderer renderer, List<Action> initActions, List<Action> turnActions) {
        this.map = map;
        this.renderer = renderer;
        this.initActions = initActions;
        this.turnActions = turnActions;
        this.turnCounter = 0;
    }


    public void startSimulation() {
        isRunning = true;

        for (Action action : initActions) {
            action.perform(map);
        }

        renderer.render(map);

        while (isRunning) {
            if (!isPaused) {
                nextTurn();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                isRunning = false;
            }
        }
    }


    public void nextTurn() {
        for (Action action : turnActions) {
            action.perform(map);
        }
        turnCounter += 1;
        System.out.println("Ход: " + turnCounter);
        renderer.render(map);

    }

    public void pauseSimulation() {
        isPaused = true;
    }

    public void resumeSimulation() {
        isPaused = false;
    }

    public boolean isPaused() {
        return isPaused;
    }
}


