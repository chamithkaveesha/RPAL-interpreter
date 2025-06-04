package tree.ast.definitions;

import standardizer.STBuilder;
import tree.ast.ASTNode;
import tree.st.*;
import tree.st.nonterminals.STAssign;
import tree.st.nonterminals.STComma;
import tree.st.nonterminals.STTau;
import tree.st.terminals.STIdentifier;
import utils.FCNSNode;

public class ASTSimultaneousDefinition extends ASTNode {
    public ASTSimultaneousDefinition() {
        super("and");
    }

    @Override
    public FCNSNode<STNode> doStandardize(FCNSNode<ASTNode> currentNode, STBuilder.StandardizationHelper helper) {
        STAssign assign = new STAssign();
        FCNSNode<STNode> assignNode = new FCNSNode<>(assign);

        STComma comma = new STComma();
        FCNSNode<STNode> commaNode = new FCNSNode<>(comma);

        STTau tau = new STTau();
        FCNSNode<STNode> tauNode = new FCNSNode<>(tau);

        FCNSNode<ASTNode> child = currentNode.getFirstChild();

        while (child != null) {
//            child.getData().setTreeNode(child);

            FCNSNode<STNode> standardizedChild = child.getData().standardize(child, helper);

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
