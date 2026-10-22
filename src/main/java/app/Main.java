package app;

import action.Action;
import action.MoveAction;
import action.ReSpawnAction;
import action.SpawnAction;
import engine.Simulation;
import model.Grass;
import model.Herbivore;
import model.Predator;
import model.Rock;
import model.SimulationMap;
import model.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        SimulationMap simulationMap = new SimulationMap(10, 10);

        SpawnAction grassSpawnAction = new SpawnAction(2, coordinates -> new Grass());
        SpawnAction rockSpawnAction = new SpawnAction(3, coordinates -> new Rock());
        SpawnAction treeSpawnAction = new SpawnAction(3, coordinates -> new Tree());

        SpawnAction predatorSpawnAction = new SpawnAction(3, coordinates -> new Predator(coordinates, 5, 2, 4));
        SpawnAction herbivoreSpawnAction = new SpawnAction(5, coordinates -> new Herbivore(coordinates, 10, 3));

        List<Action> initActions = new ArrayList<>();
        initActions.add(rockSpawnAction);
        initActions.add(treeSpawnAction);
        initActions.add(predatorSpawnAction);
        initActions.add(herbivoreSpawnAction);
        initActions.add(grassSpawnAction);


        MoveAction moveAction = new MoveAction();
        List<Action> turnActions = new ArrayList<>();
        turnActions.add(moveAction);

        ReSpawnAction grassReSpawnAction = new ReSpawnAction(2, Grass.class, count -> new SpawnAction(count, coordinates -> new Grass()));
        turnActions.add(grassReSpawnAction);

        ReSpawnAction herbivoreReSpawnAction = new ReSpawnAction(2, Herbivore.class, count -> new SpawnAction(count, coordinates -> new Herbivore(coordinates, 10, 3)));
        turnActions.add(herbivoreReSpawnAction);


        Simulation simulation = new Simulation(simulationMap, initActions, turnActions);

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

