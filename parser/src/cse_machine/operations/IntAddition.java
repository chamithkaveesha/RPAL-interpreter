package cse_machine.operations;

import cse_machine.elements.stack.DataStackElement;

public class IntAddition implements BinaryOperation {
    @Override
    public DataStackElement apply(DataStackElement left, DataStackElement right) {
        if (left.getDataType() != DataStackElement.Type.INT || right.getDataType() != DataStackElement.Type.INT) {
            throw new IllegalArgumentException("Addition requires integer operands.");
        }
        int result = left.getIntValue() + right.getIntValue();
        return new DataStackElement(DataStackElement.Type.INT, result);
    }
}
