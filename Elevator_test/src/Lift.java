import java.util.ArrayList;
import java.util.List;


public class Lift {
    private int floorNumber;
    public List<Floor> floorList = new ArrayList<>();
    private int weight;
    private int speedOfDoors;
    private int speedBetweenFloors;
    private boolean available = true;
    public String name;
    public List<Passenger> passengerList = new ArrayList<>();

    public enum liftStatus {
        Going_up, Going_down, Opening_doors, Closing_doors, Opened_doors, Closed_doors
    }

    public liftStatus status;

    public Lift(String name, int weight) {
        this.name = name;
        this.weight = weight;
        this.floorNumber = 1;
        this.status = liftStatus.Closed_doors;
        this.speedOfDoors = 1000;
        this.speedBetweenFloors = 2000;
    }

    public void detectorMovement() throws InterruptedException {
        boolean endloop=true;
        while (endloop) {
            while (passengerList != null) {
                for(int i=0; i<passengerList.size();i++){
                    if (passengerList.get(i).getStatus()==Passenger.passengerStatus.Entering_lift||passengerList.get(i).getStatus()==Passenger.passengerStatus.Leaving_lift){
                        System.out.println("Waiting for all movement to stop");
                        Thread.sleep(1000);
                        if(passengerList.get(i).getStatus()==Passenger.passengerStatus.Leaving_lift){
                            endloop=false;
                        }
                    }
                }
            }
        }

    }


    public int getFloorNumber() {
        return floorNumber;
    }

    public liftStatus getStatus() {
        return status;
    }

    public void moveLift(int destination) throws InterruptedException {
        int currentFloor = getFloorNumber();
        int distance = Math.abs(destination - currentFloor);
        int direction = Integer.compare(destination, currentFloor);
        changeStatus(getStatus(), direction);
        for (int i = 0; i < distance; i++) {
            Thread.sleep(speedBetweenFloors);
            setFloorNumber(getFloorNumber() + direction);
            System.out.println(name + " is on floor " + getFloorNumber());
            if (i == distance - 1) {
                setStatus(liftStatus.Closed_doors);
                direction = 0;
                changeStatus(getStatus(), direction);
            }
        }
    }

    public void changeStatus(liftStatus status, int direction) throws InterruptedException {
        switch (status) {
            case Opened_doors -> {

                if (direction != 0) {
                    setStatus(liftStatus.Closing_doors);
                    System.out.println("Doors closing");
                    changeStatus(getStatus(), direction);
                } else {
                    System.out.println(name + " doors are now open");
                }
            }
            case Closing_doors -> {
                Thread.sleep(speedOfDoors);
                setStatus(liftStatus.Closed_doors);
                System.out.println("Doors are now closed");
                changeStatus(getStatus(), direction);
            }
            case Closed_doors -> {

                if (direction > 0) {
                    setStatus(liftStatus.Going_up);
                    System.out.println(name + " is going up");
                } else if (direction < 0) {
                    setStatus(liftStatus.Going_down);
                    System.out.println(name + " is going down");
                } else {
                    System.out.println(name + " has arrived at the given floor");
                    setStatus(liftStatus.Opening_doors);
                    changeStatus(getStatus(), direction);
                }
            }
            case Opening_doors -> {
                System.out.println(name + " is opening its doors");
                Thread.sleep(speedOfDoors);
                setStatus(liftStatus.Opened_doors);
                changeStatus(getStatus(), direction);
            }
        }
    }


    public void pressButtonFloor(int floor) throws InterruptedException {
        if (floor <= 20 && floor > 0) {
            System.out.println("Button " + floor + " of " + getName() + " has been pushed");
            getFloor(getFloorNumber()).lift_called = false;
            Thread.sleep(1000);
            System.out.println(name + " is heading for floor " + floor);
            moveLift(floor);
        }
    }

    public void pressButtonHelp() {
        System.out.println("Calling lift dispatcher");
    }

    public void pressButtonDoors(liftStatus status) {
        if (status == liftStatus.Opening_doors) {
            setStatus(liftStatus.Opened_doors);
            System.out.println("Doors are now open.");
        } else if (status == liftStatus.Closing_doors) {
            setStatus(liftStatus.Closed_doors);
            System.out.println("Doors are now closed.");
        }
    }

    public Floor getFloor(int floorNumber) {
        return floorList.get(floorNumber - 1);
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setStatus(liftStatus status) {
        this.status = status;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
}


