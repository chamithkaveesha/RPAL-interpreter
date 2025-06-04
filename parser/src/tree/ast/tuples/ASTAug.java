package tree.ast.tuples;

import tree.ast.ASTNode;
import tree.st.STAug;
import tree.st.STBuilder;
import tree.st.STNode;
import utils.FCNSNode;

public class ASTAug extends ASTNode {
    public ASTAug() {
        super("aug");
    }

    @Override
    public FCNSNode<STNode> standardize(FCNSNode<ASTNode> currentNode, STBuilder.StandardizationHelper helper) {
        if (currentNode == null || currentNode.getData() == null) {
            throw new IllegalStateException("Aug node is not properly linked to the AST.");
        }

        // Get exactly two children as per specification
        FCNSNode<ASTNode> leftChild = currentNode.getFirstChild();
        if (leftChild == null) {
            throw new IllegalStateException("Aug node must have at least one child");
        }

        FCNSNode<ASTNode> rightChild = leftChild.getNextSibling();
        if (rightChild == null) {
            throw new IllegalStateException("Aug node must have exactly two children");
        }

        // Verify no extra children
        if (rightChild.getNextSibling() != null) {
            throw new IllegalStateException("Aug node can only have two children");
        }

        // Standardize both children
        FCNSNode<STNode> stLeft = helper.standardizeChild(leftChild);
        FCNSNode<STNode> stRight = helper.standardizeChild(rightChild);

        // Create and build the standardized node
        FCNSNode<STNode> stAug = new FCNSNode<>(new STAug());
        stAug.setFirstChild(stLeft);
        stLeft.setNextSibling(stRight);

        return stAug;
    }
}
