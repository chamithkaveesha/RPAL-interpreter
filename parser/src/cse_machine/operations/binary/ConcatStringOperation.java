package cse_machine.operations.binary;

import cse_machine.elements.stack.DataStackElement;

public class ConcatStringOperation implements BinaryOperation {
    @Override
    public DataStackElement apply(DataStackElement left, DataStackElement right) {
        if (left.getDataType() != DataStackElement.Type.STRING || right.getDataType() != DataStackElement.Type.STRING) {
            throw new IllegalArgumentException("Concatenation operation 'Conc' expects two strings.");
        }

        String result = left.getStringValue() + right.getStringValue();
        return new DataStackElement(DataStackElement.Type.STRING, result);
    }

    @Override
    public String toString() {
        return "BinaryOperation(Conc)";
    }
}
