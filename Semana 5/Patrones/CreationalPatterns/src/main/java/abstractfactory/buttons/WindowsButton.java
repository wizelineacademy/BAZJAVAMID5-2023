package abstractfactory.buttons;

public class WindowsButton implements Button {

    @Override
    public void paint() {
        System.out.println("WindowsButton created.");
    }
}
