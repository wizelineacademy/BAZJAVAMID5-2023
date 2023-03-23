package visitor.elements;

import visitor.Visitor;

public class XmlElement implements Element {
    private String uuid;

    public XmlElement(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return this.uuid;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
