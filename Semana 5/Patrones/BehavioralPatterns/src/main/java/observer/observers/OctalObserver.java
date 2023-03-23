package observer.observers;

public class OctalObserver implements Observer {

    @Override
    public void update(int number) {
        System.out.println( "Octal String: " + Integer.toOctalString( number ) );
    }
}