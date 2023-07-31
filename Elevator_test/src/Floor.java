public class Floor {
    private int floor;
    public boolean lift_called = false;
    private Lift liftOne;
    private Lift liftTwo;

    public Floor(int floor, Lift liftOne, Lift liftTwo) {
        this.floor = floor;
        this.liftOne = liftOne;
        this.liftTwo = liftTwo;
    }

    public int getFloor() {
        return floor;
    }


    public Lift getLiftOne() {
        return liftOne;
    }

    public Lift getLiftTwo() {
        return liftTwo;
    }

    public Boolean liftIsCalled() {
        return lift_called;
    }


    public void callLift(int floorNumber) throws InterruptedException {
        lift_called = true;
        if (Math.abs(getFloor() - getLiftOne().getFloorNumber()) <= Math.abs(getFloor() - getLiftTwo().getFloorNumber())&&getLiftOne().isAvailable()) {
            System.out.println(getLiftOne().getName() + " has been called to floor " + getFloor());
            getLiftOne().setAvailable(false);
            getLiftOne().moveLift(getFloor());
        } else {
            System.out.println(getLiftTwo().getName() + " has been called to floor " + getFloor());
            getLiftTwo().setAvailable(false);
            getLiftTwo().moveLift(getFloor());
        }
    }

}
