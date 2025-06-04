package tree.ast.variables;

import tree.ast.ASTNode;
import standardizer.STBuilder;
import tree.st.terminals.STEmpty;
import tree.st.STNode;
import utils.FCNSNode;

public class ASTEmpty extends ASTNode {
    public ASTEmpty() {
        super("()");
    }

    @Override
    public FCNSNode<STNode> doStandardize(FCNSNode<ASTNode> currentNode, STBuilder.StandardizationHelper helper) {
        return new FCNSNode<>(new STEmpty());
    }
}
