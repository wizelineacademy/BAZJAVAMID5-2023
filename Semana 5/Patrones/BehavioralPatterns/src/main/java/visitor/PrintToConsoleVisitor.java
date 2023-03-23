package visitor;

import visitor.elements.JsonElement;
import visitor.elements.XmlElement;

public class PrintToConsoleVisitor implements Visitor {

    @Override
    public void visit(XmlElement xe) {
        System.out.println(
                "processing an XML element with uuid: " + xe.getUuid());
    }

    @Override
    public void visit(JsonElement je) {
        System.out.println(
                "processing a JSON element with uuid: " + je.getUuid());
    }

}
