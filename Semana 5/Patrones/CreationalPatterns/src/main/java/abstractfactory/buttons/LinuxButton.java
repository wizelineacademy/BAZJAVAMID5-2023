package abstractfactory.buttons;

public class LinuxButton implements Button {

    @Override
    public void paint() {
        System.out.println("LinuxButton created.");
    }
}
