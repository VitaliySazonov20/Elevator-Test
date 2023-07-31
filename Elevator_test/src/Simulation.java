public class Simulation {
    public void simulate(Passenger passenger, int destination) throws InterruptedException {
        passenger.callLift();
        passenger.moveIntoLift();
        passenger.pressButtonFloor(destination);
        passenger.moveOutOfLift();
    }

}
