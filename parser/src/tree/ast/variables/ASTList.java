package tree.ast.variables;

import tree.ast.ASTNode;
import standardizer.STBuilder;
import tree.st.nonterminals.STComma;
import tree.st.STNode;
import utils.FCNSNode;

public class ASTList extends ASTNode {
    public ASTList() {
        super(",");
    }

    @Override
    public FCNSNode<STNode> standardize(FCNSNode<ASTNode> currentNode, STBuilder.StandardizationHelper helper) {
        if (currentNode == null || currentNode.getData() == null) {
            throw new IllegalStateException("Comma node is not properly linked to the AST.");
        }

        FCNSNode<ASTNode> child = currentNode.getFirstChild();

        if (child == null) {
            throw new IllegalStateException("ASTList node has no children.");
        }

        // Root comma node
        FCNSNode<STNode> commaNode = new FCNSNode<>(new STComma());

        // Process children and link them as siblings
        FCNSNode<STNode> previous = null;
        while (child != null) {
            FCNSNode<STNode> standardizedChild = helper.standardizeChild(child);

            if (previous == null) {
                commaNode.setFirstChild(standardizedChild);
            } else {
                previous.setNextSibling(standardizedChild);
            }

            previous = standardizedChild;
            child = child.getNextSibling();
        }

        return commaNode;
    }

}
