package builder.houseexample.rooms;

public class Room {

    private Integer numberOfWindows = 1;
    private Integer numberOfWalls = 4;


    public Integer getNumberOfWindows () {
        return numberOfWindows;
    }

    public void setNumberOfWindows (Integer numberOfWindows) {
        this.numberOfWindows = numberOfWindows;
    }

    public Integer getNumberOfWalls () {
        return numberOfWalls;
    }

    public void setNumberOfWalls (Integer numberOfWalls) {
        this.numberOfWalls = numberOfWalls;
    }
}
