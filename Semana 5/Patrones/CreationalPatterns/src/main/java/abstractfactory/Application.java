package abstractfactory;

import abstractfactory.buttons.Button;
import abstractfactory.factories.GUIFactory;

public class Application {

    private Button button;

    public Application(GUIFactory factory) {
        button = factory.createButton();
    }

    public void paint() {
        button.paint();
    }
}
