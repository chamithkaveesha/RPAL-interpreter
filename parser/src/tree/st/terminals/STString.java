package tree.st.terminals;

import cse_machine.elements.control.StringControlElement;
import tree.st.STNode;
import tree.transform.ControlStructureBuilderHelper;

public class STString extends STNode {
    private final String value;
    public STString(String value) {
        super("<STR:" + value + ">");
        this.value = value;
    }

    @Override
    public void buildControlStructure(ControlStructureBuilderHelper helper) {
        helper.addControlElement(new StringControlElement(value));
    }
}
