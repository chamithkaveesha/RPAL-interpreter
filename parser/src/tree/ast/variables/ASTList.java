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

        // Root comma node
        FCNSNode<STNode> commaNode = new FCNSNode<>(new STComma());

        // Process children and link them as siblings
        FCNSNode<STNode> previous = null;
        while (current != null) {
            FCNSNode<STNode> standardizedChild = helper.standardizeChild(current);

            if (previous == null) {
                commaNode.setFirstChild(standardizedChild);
            } else {
                previous.setNextSibling(standardizedChild);
            }

            previous = standardizedChild;
            current = current.getNextSibling();
        }

        return commaNode;
    }

}
