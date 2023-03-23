package visitor.elements;

import visitor.Visitor;

public class JsonElement implements Element {
    private String uuid;

    public JsonElement(String uuid) {
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
