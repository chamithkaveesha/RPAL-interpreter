package cse_machine.elements;

import cse_machine.ControlElementVisitor;

public class NilControlElement extends ControlElement {
    public NilControlElement() {
        super("NIL");
    }

    @Override
    public void accept(ControlElementVisitor visitor) {

    }
}
