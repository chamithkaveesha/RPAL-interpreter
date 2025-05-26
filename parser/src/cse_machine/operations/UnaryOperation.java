package cse_machine.operations;

import cse_machine.elements.stack.DataStackElement;

public interface UnaryOperation {
    DataStackElement apply(DataStackElement operand);
}
