package tree.ast.definitions;

import tree.ast.ASTNode;
import tree.st.STBuilder;
import tree.st.STNode;
import utils.FCNSNode;

public class ASTRecursiveFunction extends ASTNode {
    public ASTRecursiveFunction() {
        super("rec");
    }

    @Override
    public FCNSNode<STNode> standardize(STBuilder.StandardizationHelper helper) {
        return null;
    }
}
