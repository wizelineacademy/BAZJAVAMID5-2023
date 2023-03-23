package abstractfactory.factories;

import abstractfactory.buttons.Button;
import abstractfactory.buttons.WindowsButton;

public class WindowsFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
