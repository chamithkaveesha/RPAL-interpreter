package cse_machine.operations.unary;

import cse_machine.elements.stack.DataStackElement;
import cse_machine.elements.stack.StackElement;

public class PrintOperation implements UnaryOperation {
    // FIXME: do we need to return this
    @Override
    public DataStackElement apply(StackElement arg) {
        System.out.println(arg.toString());
        return new DataStackElement(DataStackElement.Type.STRING, arg.toString());
    }

    @Override
    public String toString() {
        return "UnaryOperation(print)";
    }
}
