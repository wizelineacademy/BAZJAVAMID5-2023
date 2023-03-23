package builder.houseexample;

import builder.houseexample.builders.HouseBuilder;

public class Director {

    private HouseBuilder houseBuilder;

    public void setHouseBuilder (HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    public void buildHouse() {
        houseBuilder.addRooms();
        houseBuilder.addPlumbingSystem();
        houseBuilder.addAirConditionerSystem();
        houseBuilder.addSecuritySystem();
        houseBuilder.paintHouse("white");
    }

    public House getFinishedHouse(){
        return houseBuilder.getHouse();
    }
}
