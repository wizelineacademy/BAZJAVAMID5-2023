package builder.houseexample.builders;

import builder.houseexample.rooms.Bathroom;
import builder.houseexample.rooms.Bedroom;
import builder.houseexample.rooms.Livingroom;

public class TwoBedroomHouseBuilder extends HouseBuilder{

    @Override
    public void addRooms () {
        getHouse().rooms.add(new Bathroom());
        getHouse().rooms.add(new Bedroom());
        getHouse().rooms.add(new Bedroom());
        getHouse().rooms.add(new Livingroom());
    }
}
