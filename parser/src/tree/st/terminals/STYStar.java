package tree.st.terminals;

import cse_machine.elements.control.YStarControlElement;
import tree.st.STNode;
import tree.transform.ControlStructureBuilderHelper;

public class STYStar extends STNode {
    public STYStar() {
        super("<Y*>");
    }

    @Override
    public void buildControlStructure(ControlStructureBuilderHelper helper) {
        // No special behavior; treated like a regular terminal
        helper.addControlElement(new YStarControlElement());
    }
}
