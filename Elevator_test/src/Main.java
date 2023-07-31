public class Main {
    public static void main(String[] args) throws InterruptedException {
        boolean endProgram = true;
        Building building = new Building();
        Passenger passenger1 = new Passenger("Dave", building.getFloor(1));
        Passenger passenger2 = new Passenger("Daisy", building.getFloor(15));
        Simulation simulation = new Simulation();
        Thread simOne = new Thread(() -> {
            try {
                simulation.simulate(passenger1, 14);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread simTwo = new Thread(() -> {
            try {
                simulation.simulate(passenger2, 1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread liftOneMovementDetector = new Thread(() -> {
            try {
                building.getLiftOne().detectorMovement();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread liftTwoMovementDetector = new Thread(() -> {
            try {
                building.getLiftTwo().detectorMovement();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        liftOneMovementDetector.start();
        liftTwoMovementDetector.start();
        simOne.start();
        Thread.sleep(100);
        simTwo.start();
        while (endProgram) {
            if (!simOne.isAlive() && !simTwo.isAlive()) {
                liftOneMovementDetector.interrupt();
                liftTwoMovementDetector.interrupt();
                endProgram = false;
            }
        }
    }
}