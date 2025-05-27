package tree.st;

import tree.transform.ControlStructureBuilderHelper;

public class STYStar extends STNode {
    public STYStar() {
        super("<Y*>");
    }

    @Override
    public void buildControlStructure(ControlStructureBuilderHelper helper) {
        // No special behavior; treated like a regular terminal
    }
}
