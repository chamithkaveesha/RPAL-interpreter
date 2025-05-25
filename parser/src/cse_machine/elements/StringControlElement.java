package cse_machine.elements;

import cse_machine.ControlElementVisitor;

public class StringControlElement extends ControlElement {
    private final String value;

    public StringControlElement(String value) {
        super("\"" + value + "\"");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void accept(ControlElementVisitor visitor) {

    }
}
