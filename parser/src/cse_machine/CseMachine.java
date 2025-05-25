package cse_machine;

import cse_machine.elements.control.*;
import cse_machine.elements.stack.DataStackElement;

public class CseMachine implements ControlElementVisitor{
    private final Control control;
    private final Stack stack;
    private final Environment environment;

    public CseMachine(Control control, Stack stack, Environment environment) {
        control.initialize();
        this.control = control;
        this.stack = stack;
        stack.initialize(environment);
        this.environment = environment;
    }

    public Control getControl() {
        return control;
    }

    public Stack getStack() {
        return stack;
    }

    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public void visitGamma(GammaControlElement element) {

    }

    @Override
    public void visitLambda(LambdaControlElement element) {

    }

    @Override
    public void visitData(IdentifierControlElement element) {

    }

    @Override
    public void visitInteger(IntegerControlElement element) {
        stack.push(new DataStackElement(DataStackElement.Type.INT, element.getValue()));
    }

    @Override
    public void visitBoolean(BooleanControlElement element) {
        stack.push(new DataStackElement(DataStackElement.Type.BOOL, element.getValue()));
    }

    @Override
    public void visitString(StringControlElement element) {
        stack.push(new DataStackElement(DataStackElement.Type.STRING, element.getValue()));
    }

    @Override
    public void visitNil(NilControlElement element) {
        stack.push(new DataStackElement(DataStackElement.Type.NIL, null));
    }

    @Override
    public void visitDummy(DummyControlElement element) {
        throw new UnsupportedOperationException("Dummy element should not be executed.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== CSE Machine State ===\n");

        sb.append("\n-- Control --\n");
        sb.append(control.toString());

        sb.append("\n-- Stack --\n");
        sb.append(stack.toString());

        sb.append("\n-- Environment --\n");
        sb.append(environment.toString());

        return sb.toString();
    }
}

