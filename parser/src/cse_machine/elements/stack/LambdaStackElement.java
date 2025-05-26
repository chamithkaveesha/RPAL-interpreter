package cse_machine.elements.stack;

import cse_machine.*;
import cse_machine.elements.control.EnvironmentControlElement;

import java.util.List;

public class LambdaStackElement extends StackElement implements CallableElement {
    private final String boundVariable;
    private final int newIndex;
    private final int environmentMarker;

    public LambdaStackElement(String boundVariable, int newIndex, int environmentMarker) {
        this.boundVariable = boundVariable;
        this.newIndex = newIndex;
        this.environmentMarker = environmentMarker;
    }

    public String getBoundVariable() {
        return boundVariable;
    }

    public int getNewIndex() {
        return newIndex;
    }

    public int getEnvironmentMarker() {
        return environmentMarker;
    }

    @Override
    public String toString() {
        return String.format(
                "LambdaStackElement(boundVariable=%s, newIndex=%d, environmentMarker=%d)",
                boundVariable, newIndex, environmentMarker
        );
    }

    @Override
    public void apply(Stack stack, Control control, EnvironmentManager envManager, List<StackElement> arguments) {
        if (arguments.size() != 1 || !(arguments.get(0) instanceof DataStackElement arg)) {
            throw new IllegalArgumentException("Lambda expects exactly one DataStackElement argument.");
        }

        EnvironmentStackElement childEnvironment = envManager.createChildEnvironment();
        Environment newEnv = childEnvironment.getEnvironment();
        newEnv.setVariable(boundVariable, arg.getIntValue());

        stack.push(new EnvironmentStackElement(childEnvironment.getNumber(), newEnv));
        control.append(new EnvironmentControlElement(childEnvironment.getNumber()));
        control.appendControlStructureByLevel(newIndex);
    }

    @Override
    public int getArity() {
        return 1;
    }
}