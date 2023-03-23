package visitor;

import visitor.elements.Element;
import visitor.elements.JsonElement;
import visitor.elements.XmlElement;

import java.util.List;

public interface Visitor {

    void visit(XmlElement xe);

    void visit(JsonElement je);

}
