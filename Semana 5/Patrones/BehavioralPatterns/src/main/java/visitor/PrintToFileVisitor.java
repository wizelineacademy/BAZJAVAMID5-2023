package visitor;

import visitor.elements.JsonElement;
import visitor.elements.XmlElement;

public class PrintToFileVisitor implements Visitor {

    @Override
    public void visit(XmlElement xe) {
        //logic to print to file
    }

    @Override
    public void visit(JsonElement je) {
        //logic to print to file
    }

}
