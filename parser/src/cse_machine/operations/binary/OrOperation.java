package cse_machine.operations.binary;

import cse_machine.elements.stack.DataStackElement;

public class OrOperation implements BinaryOperation {
    @Override
    public DataStackElement apply(DataStackElement left, DataStackElement right) {
        if (left.getDataType() != DataStackElement.Type.BOOL || right.getDataType() != DataStackElement.Type.BOOL) {
            throw new IllegalArgumentException("OR operation requires boolean operands.");
        }

        boolean result = left.getBooleanValue() || right.getBooleanValue();
        return new DataStackElement(DataStackElement.Type.BOOL, result);
    }

    @Override
    public String toString() {
        return "BinaryOperation(or)";
    }
}
