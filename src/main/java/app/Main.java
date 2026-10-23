package app;

import action.Action;
import action.ActionFactory;
import engine.Simulation;
import model.SimulationMap;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        SimulationMap simulationMap = new SimulationMap(10, 10);

        ActionFactory actionFactory = new ActionFactory();
        List<Action> initActions = actionFactory.createInitActions();
        List<Action> turnActions = actionFactory.createTurnActions();


        Simulation simulation = new Simulation(simulationMap, initActions, turnActions);

        Thread simulationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                simulation.startSimulation();
            }
        });

        simulationThread.start();

        SimulationController simulationController = new SimulationController(simulation);
        simulationController.start();


    }
}

