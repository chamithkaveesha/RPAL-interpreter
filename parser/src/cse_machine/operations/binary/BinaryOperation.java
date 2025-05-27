package cse_machine.operations.binary;

import cse_machine.elements.stack.DataStackElement;

public interface BinaryOperation {
    DataStackElement apply(DataStackElement left, DataStackElement right);
}
