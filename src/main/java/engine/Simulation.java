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


    public Simulation(SimulationMap map, List<Action> initActions, List<Action> turnActions) {

        this.map = map;
        this.renderer = new MapRenderer();
        this.initActions = initActions;
        this.turnActions = turnActions;
        this.turnCounter = 0;

        performActions(initActions);

    }

    private void performActions(List<Action> actions) {
        for (Action action : actions) {
            action.perform(map);

        }
    }


    public void startSimulation() {
        isRunning = true;

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
        performActions(turnActions);
        turnCounter++;
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


