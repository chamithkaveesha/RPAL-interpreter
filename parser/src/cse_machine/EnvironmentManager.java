package cse_machine;

import cse_machine.elements.stack.EnvironmentStackElement;

import java.util.ArrayDeque;
import java.util.Deque;

public class EnvironmentManager {
    private final Deque<EnvironmentStackElement> envStack = new ArrayDeque<>();
    private int nextEnvNumber = 1; // 0 reserved for primitive

    public void initializePrimitiveEnvironment(Environment primitiveEnv) {
        envStack.clear();
        envStack.push(new EnvironmentStackElement(0, primitiveEnv));
    }

    public EnvironmentStackElement getCurrent() {
        return envStack.peekLast();
    }

    public EnvironmentStackElement createChildEnvironment() {
        Environment parent = getCurrent().getEnvironment();
        Environment newEnv = new Environment(parent);  // Pass parent for chaining
        int number = nextEnvNumber++;
        EnvironmentStackElement elem = new EnvironmentStackElement(number, newEnv);
        envStack.addLast(elem);
        return elem;
    }

    public void removeCurrent() {
        if (envStack.isEmpty()) throw new IllegalStateException("No environment to remove.");
        envStack.removeLast();
    }

    public EnvironmentStackElement findEnvironment(int number) {
        for (EnvironmentStackElement e : envStack) {
            if (e.getNumber() == number) return e;
        }
        throw new IllegalStateException("Environment number " + number + " not found.");
    }

    public int getCurrentNumber() {
        return getCurrent().getNumber();
    }
}
