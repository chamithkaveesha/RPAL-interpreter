package tree.ast.definitions;

import tree.ast.ASTNode;
import tree.st.STAssign;
import tree.st.STBuilder;
import tree.st.STNode;
import utils.FCNSNode;

public class ASTAssign extends ASTNode {
    public ASTAssign() {
        super("=");
    }

    @Override
    public FCNSNode<STNode> standardize(STBuilder.StandardizationHelper helper) {
        if (getTreeNode() == null || getTreeNode().getFirstChild() == null || getTreeNode().getFirstChild().getNextSibling() == null) {
            throw new IllegalStateException("Assign node must have two children: a variable and an expression.");
        }

        // Get the two children: variable and expression
        FCNSNode<ASTNode> varNode = getTreeNode().getFirstChild();
        FCNSNode<ASTNode> exprNode = varNode.getNextSibling();

        // Standardize both
        FCNSNode<STNode> stdVar = helper.standardizeChild(varNode);
        FCNSNode<STNode> stdExpr = helper.standardizeChild(exprNode);

        // Create STAssign node and link children
        FCNSNode<STNode> assignNode = new FCNSNode<>(new STAssign());
        assignNode.setFirstChild(stdVar);
        stdVar.setNextSibling(stdExpr);

        return assignNode;
    }
}
