package tree.st.terminals;

import cse_machine.elements.BooleanControlElement;
import tree.st.STNode;
import tree.transform.ControlStructureBuilderHelper;

public class STBoolean extends STNode {
    private final boolean value;

    public STBoolean(Boolean value) {
        super(value != null ? "<" + value.toString() + ">" : throwIllegalArgumentException());
        this.value = value;
    }

    private boolean getValue() {
        return value;
    }

    private static String throwIllegalArgumentException() {
        throw new IllegalArgumentException("value cannot be null");
    }

    @Override
    public void buildControlStructure(ControlStructureBuilderHelper helper) {
        helper.addControlElement(new BooleanControlElement(value));
    }
}
