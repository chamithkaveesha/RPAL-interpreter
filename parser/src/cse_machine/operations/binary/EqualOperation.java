package cse_machine.operations.binary;

import cse_machine.elements.stack.DataStackElement;

public class EqualOperation implements BinaryOperation {
    @Override
    public DataStackElement apply(DataStackElement left, DataStackElement right) {
        // If types differ, not equal
        if (left.getDataType() != right.getDataType()) {
            return new DataStackElement(DataStackElement.Type.BOOL, false);
        }

        switch (left.getDataType()) {
            case INT:
                return new DataStackElement(
                        DataStackElement.Type.BOOL,
                        left.getIntValue() == right.getIntValue()
                );
            case STRING:
                return new DataStackElement(
                        DataStackElement.Type.BOOL,
                        left.getStringValue().equals(right.getStringValue())
                );
            case BOOL:
                return new DataStackElement(
                        DataStackElement.Type.BOOL,
                        left.getBooleanValue() == right.getBooleanValue()
                );
            case NIL:
                // Both are NIL, consider equal
                return new DataStackElement(DataStackElement.Type.BOOL, true);
            default:
                throw new IllegalArgumentException(
                        "Equality not supported for type: " + left.getDataType()
                );
        }
    }

    @Override
    public String toString() {
        return "BinaryOperation(generic_eq)";
    }
}
