package cse_machine.elements.stack;

import cse_machine.Environment;

public class EnvironmentStackElement extends StackElement {
    private int number;
    private final Environment environment;

    public EnvironmentStackElement(int number, Environment environment) {
        this.number = number;
        this.environment = environment;
    }

    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public String toString() {
        return "EnvFrame" + environment.toString();
    }
}
