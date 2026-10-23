package app;

import engine.Simulation;

import java.util.Scanner;

public class SimulationController {
    private final Simulation simulation;

    public SimulationController(Simulation simulation) {
        this.simulation = simulation;
    }

    public void start() {

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
