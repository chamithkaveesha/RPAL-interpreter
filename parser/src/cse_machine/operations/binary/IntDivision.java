package cse_machine.operations.binary;

import cse_machine.elements.stack.DataStackElement;

public class IntDivision implements BinaryOperation {
    @Override
    public DataStackElement apply(DataStackElement left, DataStackElement right) {
        if (left.getDataType() != DataStackElement.Type.INT || right.getDataType() != DataStackElement.Type.INT) {
            throw new IllegalArgumentException("Division requires integer operands.");
        }
        int divisor = right.getIntValue();
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        int result = left.getIntValue() / divisor;
        return new DataStackElement(DataStackElement.Type.INT, result);
    }
}
