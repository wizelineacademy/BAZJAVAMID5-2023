package visitor;

import visitor.elements.Element;
import visitor.elements.JsonElement;
import visitor.elements.XmlElement;

import java.util.UUID;

public class Client {

    public static void main(String[] args) {

        Visitor consoleVisitor = new PrintToConsoleVisitor();
        Visitor fileVisitor = new PrintToFileVisitor();

        Element jsonElement = new JsonElement(generateUuid());
        jsonElement.accept(consoleVisitor);
        jsonElement.accept(fileVisitor);

        Element xmlElement = new XmlElement(generateUuid());
        xmlElement.accept(consoleVisitor);
        xmlElement.accept(fileVisitor);

    }

    private static String generateUuid() {
        return UUID.randomUUID()
                .toString();
    }
}
