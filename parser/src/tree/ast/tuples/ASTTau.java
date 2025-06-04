package tree.ast.tuples;

import tree.ast.ASTNode;
import standardizer.STBuilder;
import tree.st.STNode;
import tree.st.nonterminals.STTau;
import utils.FCNSNode;

public class ASTTau extends ASTNode {
    public ASTTau() {
        super("tau");
    }

    @Override
    public FCNSNode<STNode> doStandardize(FCNSNode<ASTNode> currentNode, STBuilder.StandardizationHelper helper) {
        // Create the standardized tau node
        FCNSNode<STNode> stTau = new FCNSNode<>(new STTau());

        // Standardize all children (Ta elements)
        FCNSNode<ASTNode> child = currentNode.getFirstChild();
        FCNSNode<STNode> previousSTChild = null;

        while (child != null) {
            // Standardize the current child
            FCNSNode<STNode> standardizedChild = helper.standardizeChild(child);

            // Build the sibling chain
            if (previousSTChild == null) {
                // First child becomes the head
                stTau.setFirstChild(standardizedChild);
            } else {
                // Link to previous sibling
                previousSTChild.setNextSibling(standardizedChild);
            }

            previousSTChild = standardizedChild;
            child = child.getNextSibling();
        }

        return stTau;
    }
}
