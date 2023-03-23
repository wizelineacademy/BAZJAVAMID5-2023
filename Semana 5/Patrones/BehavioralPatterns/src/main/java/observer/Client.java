package observer;

import observer.observers.BinaryObserver;
import observer.observers.HexaObserver;
import observer.observers.OctalObserver;

public class Client {

    public static void main(String[] args) {
        Observable observable = new Observable();

        observable.attach(new BinaryObserver());
        observable.attach(new OctalObserver());
        observable.attach(new HexaObserver());

        System.out.println("- Primer cambio de estado: 15");
        observable.setNumber(15);
        System.out.println("=====================");
        System.out.println("- Segundo cambio de estado: 10");
        observable.setNumber(10);
    }
}
