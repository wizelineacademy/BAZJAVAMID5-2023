package abstractfactory.factories;

import abstractfactory.buttons.Button;
import abstractfactory.buttons.LinuxButton;

public class LinuxFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new LinuxButton();
    }
}
