package cse_machine.elements;

import cse_machine.ControlElementVisitor;

public class DummyControlElement extends ControlElement {
    public DummyControlElement() {
        super("DUMMY");
    }

    @Override
    public void accept(ControlElementVisitor visitor) {

    }
}
