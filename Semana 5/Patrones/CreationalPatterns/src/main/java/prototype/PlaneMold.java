package prototype;

public interface PlaneMold extends Cloneable {

    String getColor();

    void setColor(String color);

    int getNumberOfSeats();

    void setNumberOfSeats(int numberOfSeats);

    void fly();

    PlaneMold clone();

}
