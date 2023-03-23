package observer.observers;

public class HexaObserver implements Observer {

    @Override
    public void update(int number) {
        System.out.println( "Hex String: " + Integer.toHexString( number ).toUpperCase() );
    }
}
