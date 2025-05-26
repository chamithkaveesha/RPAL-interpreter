package cse_machine.operations;

import cse_machine.elements.stack.DataStackElement;

public class NegateOperation implements UnaryOperation {
    @Override
    public DataStackElement apply(DataStackElement operand) {
        if (operand.getDataType() != DataStackElement.Type.INT) {
            throw new IllegalArgumentException("Negate operation expects an integer.");
        }

        int value = (Integer) operand.getValue();
        return new DataStackElement(DataStackElement.Type.INT, -value);
    }

    @Override
    public String toString() {
        return "UnaryOperation(neg)";
    }
}
