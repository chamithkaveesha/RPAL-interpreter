package tree.st;

import tree.ast.ASTNode;
import utils.FCNSNode;

public class STBuilder {

    public interface StandardizationHelper {
        FCNSNode<STNode> standardizeChild(FCNSNode<ASTNode> child);
    }

    public static FCNSNode<STNode> build(FCNSNode<ASTNode> astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException("AST node cannot be null");
        }
        FCNSNode<STNode> stRoot = standardizeNode(astNode, new StandardizationHelper() {
            @Override
            public FCNSNode<STNode> standardizeChild(FCNSNode<ASTNode> child) {
                return standardizeNode(child, this);
            }
        });

        if (stRoot == null) {
            return null;
        }

        setTreeNodeReferences(stRoot);
        return stRoot;
    }

    private static FCNSNode<STNode> standardizeNode(FCNSNode<ASTNode> astNode, StandardizationHelper helper) {
        if (astNode == null) {
            return null;
        }

        // Set the treeNode reference in the ASTNode
        astNode.getData().setTreeNode(astNode);

        // Delegate standardization to the ASTNode, passing the helper
        return astNode.getData().standardize(helper);
    }

    private static void setTreeNodeReferences(FCNSNode<STNode> root) {
        if (root == null) return;

        // Set the treeNode reference in the contained STNode
        root.getData().setTreeNode(root);

        // Recursively for first child
        setTreeNodeReferences(root.getFirstChild());

        // Recursively for siblings
        setTreeNodeReferences(root.getNextSibling());
    }
}