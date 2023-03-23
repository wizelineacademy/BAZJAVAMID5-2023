package visitor.elements;

import visitor.Visitor;

public interface Element {

    void accept(Visitor v);
}
