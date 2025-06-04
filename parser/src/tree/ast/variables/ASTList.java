package tree.ast.variables;

import tree.ast.ASTNode;
import standardizer.STBuilder;
import tree.st.nonterminals.STComma;
import tree.st.STNode;
import utils.FCNSNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list node in the AST, Corresponding to comma-separated values
 * such as function parameters, bindings, and tuples.
 *
 * <p>This node standardizes into an {@link STComma} node where each child
 * is a standardized version of the original AST list elements.
 */
public class ASTList extends ASTNode {
    public ASTList() {
        super(",");
    }

    @Override
    public FCNSNode<STNode> doStandardize(FCNSNode<ASTNode> currentNode, STBuilder.StandardizationHelper helper) {
        if (currentNode == null || currentNode.getData() == null) {
            throw new IllegalStateException("Comma node is not properly linked to the AST.");
        }

        FCNSNode<ASTNode> child = currentNode.getFirstChild();

        if (child == null) {
            throw new IllegalStateException("ASTList node has no children.");
        }

        FCNSNode<STNode> commaNode = new FCNSNode<>(new STComma());

        List<FCNSNode<STNode>> childSTs = new ArrayList<>();
        while (child != null) {
            childSTs.add(helper.standardizeChild(child));
            child = child.getNextSibling();
        }
        linkSiblings(commaNode, childSTs);

        return commaNode;
    }

    /**
     * Links a list of {@code FCNSNode<STNode>} children as siblings
     * under the given parent node using the FCNS representation.
     *
     * @param parent the parent node to attach the children to
     * @param children the list of children to link
     */
    private void linkSiblings(FCNSNode<STNode> parent, List<FCNSNode<STNode>> children) {
        FCNSNode<STNode> prev = null;
        for (FCNSNode<STNode> child : children) {
            if (prev == null) {
                parent.setFirstChild(child);
            } else {
                prev.setNextSibling(child);
            }
            prev = child;
        }
    }

}
