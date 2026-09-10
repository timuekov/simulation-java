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

        simulation.startSimulation();
    }
}

