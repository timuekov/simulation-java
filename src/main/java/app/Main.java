package app;

import action.Action;
import action.GrassSpawnAction;
import action.HerbivoreSpawnAction;
import action.MaintainGrassAction;
import action.MaintainHerbivoreAction;
import action.MoveAction;
import action.PredatorSpawnAction;
import action.RockSpawnAction;
import action.TreeSpawnAction;
import engine.Simulation;
import model.SimulationMap;
import view.MapRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SimulationMap simulationMap = new SimulationMap(5, 5);
        MapRenderer mapRenderer = new MapRenderer();

        RockSpawnAction rockSpawnAction = new RockSpawnAction(3);
        TreeSpawnAction treeSpawnAction = new TreeSpawnAction(3);
        PredatorSpawnAction predatorSpawnAction = new PredatorSpawnAction(3, 5, 2, 4);
        HerbivoreSpawnAction herbivoreSpawnAction = new HerbivoreSpawnAction(5, 10, 3);
        GrassSpawnAction grassSpawnAction = new GrassSpawnAction(2);

        List<Action> initActions = new ArrayList<>();
        initActions.add(rockSpawnAction);
        initActions.add(treeSpawnAction);
        initActions.add(predatorSpawnAction);
        initActions.add(herbivoreSpawnAction);
        initActions.add(grassSpawnAction);


        MoveAction moveAction = new MoveAction();
        List<Action> turnActions = new ArrayList<>();
        turnActions.add(moveAction);

        MaintainGrassAction maintainGrassAction = new MaintainGrassAction(2);
        turnActions.add(maintainGrassAction);

        MaintainHerbivoreAction maintainHerbivoreAction = new MaintainHerbivoreAction(2, 10, 3);
        turnActions.add(maintainHerbivoreAction);


        Simulation simulation = new Simulation(simulationMap, mapRenderer, initActions, turnActions);

        Thread simulationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                simulation.startSimulation();
            }
        });

        simulationThread.start();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Нажмите Enter, чтобы поставить симуляцию на паузу.");
        while (true) {

            scanner.nextLine();

            if (!simulation.isPaused()) {
                simulation.pauseSimulation();
                System.out.println("Симуляция на паузе. Нажмите Enter для продолжения.");
            } else {
                simulation.resumeSimulation();
                System.out.println("Симуляция продолжена. Нажмите Enter для паузы.");
            }
        }


    }
}

