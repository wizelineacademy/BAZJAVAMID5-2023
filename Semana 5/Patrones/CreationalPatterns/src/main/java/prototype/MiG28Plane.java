package prototype;

public class MiG28Plane implements PlaneMold, Cloneable {
    private String color;

    private int numberOfSeats;

    public MiG28Plane() { }

    public MiG28Plane(MiG28Plane from) {
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
        System.out.println("Volando desde un MiG28 of color " + color + ", con total de asientos de " + numberOfSeats);
    }

    @Override
    public PlaneMold clone() {
        return new MiG28Plane(this);
    }
}
