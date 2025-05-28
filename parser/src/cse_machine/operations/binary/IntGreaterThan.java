package cse_machine.operations.binary;

import cse_machine.elements.stack.DataStackElement;

public class IntGreaterThan implements BinaryOperation {
    @Override
    public DataStackElement apply(DataStackElement left, DataStackElement right) {
        if (left.getDataType() != DataStackElement.Type.INT || right.getDataType() != DataStackElement.Type.INT) {
            throw new IllegalArgumentException("Greater-than operation requires integer operands.");
        }
        boolean result = left.getIntValue() > right.getIntValue();
        return new DataStackElement(DataStackElement.Type.BOOL, result);
    }
}
