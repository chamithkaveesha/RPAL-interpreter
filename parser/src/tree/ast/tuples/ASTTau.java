package tree.ast.tuples;

import tree.ast.ASTNode;
import tree.st.STBuilder;
import tree.st.STNode;
import tree.st.STTau;
import utils.FCNSNode;

public class ASTTau extends ASTNode {
    public ASTTau() {
        super("tau");
    }

    @Override
    public FCNSNode<STNode> standardize(STBuilder.StandardizationHelper helper) {
        if (getTreeNode() == null) {
            throw new IllegalStateException("Tau node is not properly linked to the AST.");
        }

        // Create the standardized tau node
        FCNSNode<STNode> stTau = new FCNSNode<>(new STTau());

        // Standardize all children (Ta elements)
        FCNSNode<ASTNode> currentASTChild = getTreeNode().getFirstChild();
        FCNSNode<STNode> previousSTChild = null;

        while (currentASTChild != null) {
            // Standardize the current child
            FCNSNode<STNode> stChild = helper.standardizeChild(currentASTChild);

            // Build the sibling chain
            if (previousSTChild == null) {
                // First child becomes the head
                stTau.setFirstChild(stChild);
            } else {
                // Link to previous sibling
                previousSTChild.setNextSibling(stChild);
            }

            previousSTChild = stChild;
            currentASTChild = currentASTChild.getNextSibling();
        }

        return stTau;
    }
}
