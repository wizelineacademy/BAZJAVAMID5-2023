package builder.houseexample;

import builder.houseexample.rooms.Room;

import java.util.ArrayList;
import java.util.List;

public class House {

    public List<Room> rooms = new ArrayList<>();
    private String exteriorColor = "white";

    public String getExteriorColor () {
        return exteriorColor;
    }

    public void setExteriorColor (String exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

    @Override
    public String toString () {
        System.out.println("house has total rooms " + rooms.size());
        System.out.println("house has color " + getExteriorColor());
        return "";
    }
}
