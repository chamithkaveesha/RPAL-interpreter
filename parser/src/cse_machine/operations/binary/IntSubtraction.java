package cse_machine.operations.binary;

import cse_machine.elements.stack.DataStackElement;

public class IntSubtraction implements BinaryOperation {
    @Override
    public DataStackElement apply(DataStackElement left, DataStackElement right) {
        if (left.getDataType() != DataStackElement.Type.INT || right.getDataType() != DataStackElement.Type.INT) {
            throw new IllegalArgumentException("Subtraction requires integer operands.");
        }
        int result = left.getIntValue() - right.getIntValue();
        return new DataStackElement(DataStackElement.Type.INT, result);
    }
}
