import java.util.ArrayList;
import java.util.List;

public class Building {
    private Lift liftOne;
    private Lift liftTwo;
    public List<Floor> floorList = new ArrayList<>();

    public Building() {
        for (int i = 0; i < 20; i++) {
            Floor floor = new Floor(i + 1, getLiftOne(), getLiftTwo());
            getLiftOne().floorList.add(floor);
            getLiftTwo().floorList.add(floor);
            floorList.add(floor);
        }

    }

    public Lift getLiftOne() {
        if (liftOne == null) {
            liftOne = new Lift("Lift One", 5);
        }
        return liftOne;
    }

    public Lift getLiftTwo() {
        if (liftTwo == null) {
            liftTwo = new Lift("Lift Two", 10);
        }
        return liftTwo;
    }

    public Floor getFloor(int floorNumber) {
        return floorList.get(floorNumber - 1);
    }
}
