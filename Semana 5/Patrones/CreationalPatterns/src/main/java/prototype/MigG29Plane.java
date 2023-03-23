package prototype;

public class MigG29Plane implements PlaneMold, Cloneable {

    private String color;

    private int numberOfSeats;

    public MigG29Plane() {
    }

    public MigG29Plane(MigG29Plane from) {
        this.color = from.color;
        this.numberOfSeats = from.numberOfSeats;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    @Override
    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }


    @Override
    public void fly() {
        System.out.println("Volando desde un MigG29 of color " + color + ", con total de asientos de " + numberOfSeats);
    }

    @Override
    public PlaneMold clone() {
        return new MigG29Plane(this);
    }
}
