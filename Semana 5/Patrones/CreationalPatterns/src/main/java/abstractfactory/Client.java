package abstractfactory;

import abstractfactory.factories.GUIFactory;
import abstractfactory.factories.LinuxFactory;
import abstractfactory.factories.MacOSFactory;
import abstractfactory.factories.WindowsFactory;

import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Application app;

        System.out.println("Choose an OS to render the app?");
        System.out.println("1.- MacOs");
        System.out.println("2.- Windows");
        System.out.println("3.- Linux");
        Scanner scan = new Scanner(System.in);
        int osOption = scan.nextInt();

        GUIFactory guiFactory = getFactory(osOption);

        app = new Application(guiFactory);
        app.paint();
    }

    // Abstract Factory
    private static GUIFactory getFactory(int factoryType) {
        if (factoryType == 1) {
            return new MacOSFactory();
        } else if (factoryType == 2){
            return new WindowsFactory();
        } else {
            return new LinuxFactory();
        }
    }
}
