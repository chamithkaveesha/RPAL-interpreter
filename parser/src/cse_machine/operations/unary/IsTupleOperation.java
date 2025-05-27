package cse_machine.operations.unary;

import cse_machine.elements.stack.DataStackElement;
import cse_machine.elements.stack.StackElement;
import cse_machine.elements.stack.TupleStackElement;

public class IsTupleOperation implements UnaryOperation {
    @Override
    public DataStackElement apply(StackElement operand) {
        return new DataStackElement(DataStackElement.Type.BOOL, operand instanceof TupleStackElement);
    }

    @Override
    public String toString() {
        return "UnaryOperation(istuple)";
    }
}
