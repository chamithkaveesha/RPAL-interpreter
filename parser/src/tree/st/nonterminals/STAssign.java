package tree.st.nonterminals;

import tree.st.STNode;
import tree.transform.ControlStructureBuilderHelper;

public class STAssign extends STNode {
    public STAssign() {
        super("=");
    }

    @Override
    public void buildControlStructure(ControlStructureBuilderHelper helper) {

    }
}
