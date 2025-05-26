package tree.st.terminals;

import tree.st.STNode;
import tree.transform.ControlStructureBuilderHelper;

// TODO; check proper execution
public class STEmpty extends STNode {
    public STEmpty() {
        super("()");
    }

    @Override
    public void buildControlStructure(ControlStructureBuilderHelper helper) {
        // TODO
    }
}
