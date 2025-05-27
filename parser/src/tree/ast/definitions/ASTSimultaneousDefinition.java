package tree.ast.definitions;

import tree.ast.ASTNode;
import tree.st.*;
import utils.FCNSNode;

public class ASTSimultaneousDefinition extends ASTNode {
    public ASTSimultaneousDefinition() {
        super("and");
    }

    @Override
    public FCNSNode<STNode> standardize(STBuilder.StandardizationHelper helper) {
        if (getTreeNode() == null) {
            throw new IllegalStateException("Simultaneous definition node is not properly linked to the AST.");
        }

        STAssign assign = new STAssign();
        FCNSNode<STNode> assignNode = new FCNSNode<>(assign);
        assign.setTreeNode(assignNode);

        STComma comma = new STComma();
        FCNSNode<STNode> commaNode = new FCNSNode<>(comma);
        comma.setTreeNode(commaNode);

        STTau tau = new STTau();
        FCNSNode<STNode> tauNode = new FCNSNode<>(tau);
        tau.setTreeNode(tauNode);

        // Traverse each child '=' node under 'and'
        FCNSNode<ASTNode> defNode = this.getTreeNode().getFirstChild();
        while (defNode != null) {
            if (!defNode.getData().getLabel().equals("=") || defNode.getData() == null || defNode.getFirstChild() == null) {
                throw new IllegalStateException("Expected '=' node with a valid structure inside 'and'");
            }

            // Left: ID (X), Right: expression (E)
            FCNSNode<ASTNode> idNode = defNode.getFirstChild();
            FCNSNode<ASTNode> exprNode = idNode.getNextSibling();

            if (idNode == null || exprNode == null) {
                throw new IllegalStateException("Each '=' in 'and' must have both identifier and expression");
            }

            // Standardize ID and E
            FCNSNode<STNode> stdX = helper.standardizeChild(idNode);
            FCNSNode<STNode> stdE = helper.standardizeChild(exprNode);

            // Add to comma and tau respectively
            commaNode.addChildNode(stdX);
            tauNode.addChildNode(stdE);

            defNode = defNode.getNextSibling();
        }

        // Build: = (commaNode) (tauNode)
        assignNode.setFirstChild(commaNode);
        commaNode.setNextSibling(tauNode);

        return assignNode;
    }
}
