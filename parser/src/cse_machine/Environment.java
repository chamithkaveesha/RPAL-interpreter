package cse_machine;

import cse_machine.elements.stack.StackElement;
import cse_machine.elements.stack.DataStackElement;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    private final Map<String, StackElement> variables = new HashMap<>();
    private final Environment parent;  // enclosing environment (outer scope)

    public Environment(Environment parent) {
        this.parent = parent;
    }

    public Environment() {
        this(null);
    }

    /**
     * Define or update a variable in the current environment frame.
     */
    public void setVariable(String name, StackElement value) {
        variables.put(name, value);
    }

    public void setVariable(String name, int value) {
        this.variables.put(name, new DataStackElement(DataStackElement.Type.INT, value));
    }

    /**
     * Lookup a variable value, searching parent environments if not found locally.
     * Returns null if not found.
     */
    public StackElement getVariable(String name) {
        StackElement value = variables.get(name);
        if (value != null) {
            return value;
        }
        if (parent != null) {
            return parent.getVariable(name);
        }
        return null;
    }

    /**
     * Check if variable is defined in this frame (not checking parents).
     */
    public boolean containsVariable(String name) {
        return variables.containsKey(name);
    }

    public Environment getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "EnvFrame" + variables.toString();
    }
}
