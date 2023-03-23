package abstractfactory.buttons;

public class MacOsButton implements Button {

    @Override
    public void paint() {
        System.out.println("MacOSButton created.");
    }
}
