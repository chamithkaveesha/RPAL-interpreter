package tree.st.terminals;

import cse_machine.elements.control.DummyControlElement;
import tree.st.STNode;
import tree.transform.ControlStructureBuilderHelper;

public class STDummy extends STNode {
    public STDummy() {
        super("dummy");
    }

    @Override
    public void buildControlStructure(ControlStructureBuilderHelper helper) {
        helper.addControlElement(new DummyControlElement());
    }
}
