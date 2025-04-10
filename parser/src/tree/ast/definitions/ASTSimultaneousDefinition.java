package tree.ast.definitions;

import tree.ast.ASTNode;
import tree.st.STBuilder;
import tree.st.STNode;
import utils.FCNSNode;

public class ASTSimultaneousDefinition extends ASTNode {
    public ASTSimultaneousDefinition() {
        super("and");
    }

    @Override
    public FCNSNode<STNode> standardize(STBuilder.StandardizationHelper helper) {
        return null;
    }
}
