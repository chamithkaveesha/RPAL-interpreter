package cse_machine.elements.control;

import cse_machine.ControlElementVisitor;

public class LambdaControlElement extends ControlElement {
    private final String boundVariable;
    private final int newIndex;

    public LambdaControlElement(String boundVariable, int newIndex) {
        super(String.format("Lambda(%d, %s)", newIndex, boundVariable));
        this.boundVariable = boundVariable;
        this.newIndex = newIndex;
    }

    public String getBoundVariable() {
        return boundVariable;
    }

    public int getNewIndex() {
        return newIndex;
    }

    @Override
    public void accept(ControlElementVisitor visitor) {

    }
}
