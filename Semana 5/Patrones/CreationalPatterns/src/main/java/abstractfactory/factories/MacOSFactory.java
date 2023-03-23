package abstractfactory.factories;

import abstractfactory.buttons.Button;
import abstractfactory.buttons.MacOsButton;

public class MacOSFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new MacOsButton();
    }
}
