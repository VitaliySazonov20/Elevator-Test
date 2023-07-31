public class Passenger {
    private int floorNumber;
    private Floor actualFloor;
    public int timeToMove;
    private Lift liftOne;
    private Lift liftTwo;
    private String name;

    public enum passengerStatus {
        Outside_lift, Inside_lift, Entering_lift, Leaving_lift
    }

    public passengerStatus status;

    public Passenger(String Name, Floor actualFloor) {
        this.name=Name;
        this.actualFloor = actualFloor;
        this.floorNumber = actualFloor.getFloor();
        this.status = passengerStatus.Outside_lift;
        this.timeToMove = 3000;
        this.liftOne = actualFloor.getLiftOne();
        this.liftTwo = actualFloor.getLiftTwo();
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void callLift() throws InterruptedException {
        getActualFloor().callLift(floorNumber);
    }

    public Lift getLiftOne() {
        return liftOne;
    }

    public Lift getLiftTwo() {
        return liftTwo;
    }

    public Floor getActualFloor() {
        return actualFloor;
    }

    public void setActualFloor(Floor actualFloor) {
        this.actualFloor = actualFloor;
        this.floorNumber = actualFloor.getFloor();
    }

    public void pressButtonFloor(int floor) throws InterruptedException {
        if (getStatus() == passengerStatus.Inside_lift) {
            if (floor <= 20 && floor > 0) {
                if (getLiftOne().passengerList.contains(this)) {
                    getLiftOne().pressButtonFloor(floor);
                } else if (getLiftTwo().passengerList.contains(this)) {
                    getLiftTwo().pressButtonFloor(floor);
                }
            }
        }
    }

    public void changeStatus(passengerStatus status) throws InterruptedException {
        switch (status) {
            case Outside_lift -> {
                if(getLiftOne().passengerList.size()!=0){
                    System.out.println(getName()+" has left "+ getLiftOne().getName());
                    getLiftOne().passengerList.remove(this);
                }
                if(getLiftTwo().passengerList.size()!=0){
                    System.out.println(getName()+" has left "+ getLiftTwo().getName());
                    getLiftTwo().passengerList.remove(this);
                }
                if(getActualFloor().liftIsCalled()){
                setStatus(passengerStatus.Entering_lift);
                changeStatus(this.status);}
            }
            case Entering_lift -> {
                if (getLiftOne().getFloorNumber() == getFloorNumber()) {
                    getLiftOne().passengerList.add(this);
                    System.out.println(getName()+" is entering "+ getLiftOne().getName());
                } else {
                    getLiftTwo().passengerList.add(this);
                    System.out.println(getName()+" is entering "+ getLiftOne().getName());

                }
                Thread.sleep(timeToMove);
                setStatus(passengerStatus.Inside_lift);
            }
            case Inside_lift -> {
                setStatus(passengerStatus.Leaving_lift);
                System.out.println(getName()+" is inside a lift");
                changeStatus(getStatus());
            }
            case Leaving_lift -> {
                if (getLiftOne().passengerList != null) {
                    System.out.println(getName()+" is leaving "+ getLiftOne().getName());
                    Thread.sleep(timeToMove);
                    setActualFloor(getLiftOne().getFloor(getFloorNumber()));
                    getLiftOne().setAvailable(true);
                } else {
                    System.out.println(getName()+" is leaving "+ getLiftTwo().getName());
                    Thread.sleep(timeToMove);
                    setActualFloor(getLiftTwo().getFloor(getFloorNumber()));
                    getLiftTwo().setAvailable(true);
                }
                setStatus(passengerStatus.Outside_lift);
                changeStatus(getStatus());
            }
        }
    }

    public passengerStatus getStatus() {
        return status;
    }

    public void moveIntoLift() throws InterruptedException {
            changeStatus(getStatus());
    }

    public void moveOutOfLift() throws InterruptedException {
            changeStatus(getStatus());
    }
    public String getName(){
        return name;
    }

    public void setStatus(passengerStatus status) {
        this.status = status;
    }
}
