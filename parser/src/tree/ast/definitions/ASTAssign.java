package tree.ast.definitions;

import tree.ast.ASTNode;
import tree.st.STBuilder;
import tree.st.STNode;
import utils.FCNSNode;

public class ASTAssign extends ASTNode {
    public ASTAssign() {
        super("=");
    }

    @Override
    public FCNSNode<STNode> standardize(STBuilder.StandardizationHelper helper) {
        return null;
    }
}
