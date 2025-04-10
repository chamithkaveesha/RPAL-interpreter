package tree.ast.definitions;

import tree.ast.ASTNode;
import tree.st.STBuilder;
import tree.st.STNode;
import utils.FCNSNode;

public class ASTWithin extends ASTNode {
    public ASTWithin() {
        super("within");
    }

    @Override
    public FCNSNode<STNode> standardize(STBuilder.StandardizationHelper helper) {
        return null;
    }
}
