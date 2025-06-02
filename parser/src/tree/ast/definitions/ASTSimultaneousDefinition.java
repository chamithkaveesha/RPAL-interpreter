package tree.ast.definitions;

import tree.ast.ASTNode;
import tree.st.*;
import tree.st.terminals.STIdentifier;
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

        FCNSNode<ASTNode> child = this.getTreeNode().getFirstChild();

        while (child != null) {
            child.getData().setTreeNode(child);

            FCNSNode<STNode> standardizedChild = child.getData().standardize(helper);

            if (standardizedChild == null || standardizedChild.getData() == null) {
                throw new IllegalStateException("Standardized child is null or has no data.");
            }

            String label = standardizedChild.getData().getLabel();

            if (!label.equals("=")) {
                throw new IllegalStateException("Expected '=' at root of standardized child under 'and', but found: " + label);
            }

            FCNSNode<STNode> idNode = standardizedChild.getFirstChild();
            if (idNode == null) {
                throw new IllegalStateException("Left-hand side (ID) of '=' node is missing");
            }

            FCNSNode<STNode> exprNode = idNode.getNextSibling();
            if (exprNode == null) {
                throw new IllegalStateException("Right-hand side (expression) of '=' node is missing");
            }

            if (!(idNode.getData() instanceof STIdentifier id)) {
                throw new IllegalStateException("Expected STIdentifier in the LHS of '=' under 'and'");
            }

            commaNode.addChildNode(new FCNSNode<>(new STIdentifier(id.getName())));

            tauNode.addChildNode(exprNode);

            child = child.getNextSibling();
        }

        assignNode.setFirstChild(commaNode);
        commaNode.setNextSibling(tauNode);
        return assignNode;
    }

}
