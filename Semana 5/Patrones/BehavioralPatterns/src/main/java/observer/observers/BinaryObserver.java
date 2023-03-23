package observer.observers;

public class BinaryObserver implements Observer {

    @Override
    public void update(int number) {
        System.out.println( "Binary String: " + Integer.toBinaryString( number ) );
    }
}