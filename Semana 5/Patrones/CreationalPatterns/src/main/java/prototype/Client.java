package prototype;

import java.util.Scanner;

public class Client {

    public static void main (String[] args) throws CloneNotSupportedException {
        System.out.println("How many Planes you want to create based on Mold / Prototype ?");
        Scanner scan = new Scanner(System.in);
        int quantity = scan.nextInt();

        PlaneMold planeMold = new MigG29Plane();
        planeMold.setColor("rojo");
        planeMold.setNumberOfSeats(10);

        for(int i=0;i<quantity;i++){
            PlaneMold clonedPlane = planeMold.clone();
            clonedPlane.setColor("azul");
            clonedPlane.fly();
        }

    }
}