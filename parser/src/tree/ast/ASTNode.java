package tree.ast;

import tree.Node;
import tree.st.STBuilder;
import tree.st.STNode;
import utils.FCNSNode;

public abstract class ASTNode extends Node {
    public ASTNode(String lexeme) {
        super(lexeme);
    }

    public abstract FCNSNode<STNode> standardize(
            FCNSNode<ASTNode> currentNode,
            STBuilder.StandardizationHelper helper);
}
