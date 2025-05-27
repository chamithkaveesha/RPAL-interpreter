package cse_machine.operations.unary;

import cse_machine.elements.stack.DataStackElement;

public class NotOperation implements UnaryOperation {
    @Override
    public DataStackElement apply(DataStackElement operand) {
        if (operand.getDataType() != DataStackElement.Type.BOOL) {
            throw new IllegalArgumentException("Not operation expects a boolean.");
        }

        boolean value = (Boolean) operand.getValue();
        return new DataStackElement(DataStackElement.Type.BOOL, !value);
    }

    @Override
    public String toString() {
        return "UnaryOperation(not)";
    }
}
