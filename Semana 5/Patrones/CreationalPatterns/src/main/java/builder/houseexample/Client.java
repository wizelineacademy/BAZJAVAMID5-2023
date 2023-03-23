package builder.houseexample;

import builder.houseexample.builders.OneBedroomHouseBuilder;
import builder.houseexample.builders.TwoBedroomHouseBuilder;

public class Client {

    public static void main (String[] args) {
        Director director = new Director();

        // Hiding complexity and building one bedroom house
        director.setHouseBuilder(new OneBedroomHouseBuilder());
        director.buildHouse();
        House house = director.getFinishedHouse();
        System.out.println(house);

        System.out.println("---------------------");

        // Hiding complexity and building two bedroom house
        director.setHouseBuilder(new TwoBedroomHouseBuilder());
        director.buildHouse();
        House secondHouse = director.getFinishedHouse();
        System.out.println(secondHouse);

    }
}
