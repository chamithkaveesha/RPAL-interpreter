package tree.ast.variables;

import tree.ast.ASTNode;
import tree.st.STBuilder;
import tree.st.STComma;
import tree.st.STNode;
import utils.FCNSNode;

public class ASTList extends ASTNode {
    public ASTList() {
        super(",");
    }

    @Override
    public FCNSNode<STNode> standardize(STBuilder.StandardizationHelper helper) {
        FCNSNode<ASTNode> current = getTreeNode().getFirstChild();
        if (current == null) {
            throw new IllegalStateException("ASTList node has no children.");
        }

        // First standardized node
        FCNSNode<STNode> stFirst = helper.standardizeChild(current);
        current = current.getNextSibling();

        if (current == null) {
            return stFirst;  // Only one element, not really a list
        }

        // Build nested comma STNodes
        FCNSNode<STNode> comma = new FCNSNode<>(new STComma());
        comma.setFirstChild(stFirst);
        FCNSNode<STNode> currentComma = comma;

        while (current != null) {
            FCNSNode<STNode> standardizedChild = helper.standardizeChild(current);
            FCNSNode<STNode> nextComma = null;

            if (current.getNextSibling() != null) {
                // Create a new comma node to continue nesting
                nextComma = new FCNSNode<>(new STComma());
                standardizedChild.setNextSibling(nextComma);
            }

            currentComma.getFirstChild().setNextSibling(standardizedChild);
            currentComma = nextComma;
            current = current.getNextSibling();
        }

        return comma;
    }

}
