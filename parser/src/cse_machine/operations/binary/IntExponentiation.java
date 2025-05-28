package cse_machine.operations.binary;

import cse_machine.elements.stack.DataStackElement;

public class IntExponentiation implements BinaryOperation {
    @Override
    public DataStackElement apply(DataStackElement left, DataStackElement right) {
        if (left.getDataType() != DataStackElement.Type.INT || right.getDataType() != DataStackElement.Type.INT) {
            throw new IllegalArgumentException("Exponentiation requires integer operands.");
        }

        int base = left.getIntValue();
        int exponent = right.getIntValue();

        if (exponent < 0) {
            throw new ArithmeticException("Negative exponents are not supported for integer exponentiation.");
        }

        int result = (int) Math.pow(base, exponent);
        return new DataStackElement(DataStackElement.Type.INT, result);
    }
}
