package tree.ast.operators;

import tree.ast.ASTNode;
import tree.st.STBuilder;
import tree.st.STGamma;
import tree.st.STNode;
import utils.FCNSNode;

public class ASTInfixFunction extends ASTNode {
    public ASTInfixFunction() {
        super("@");
    }

    @Override
    public FCNSNode<STNode> standardize(FCNSNode<ASTNode> currentNode, STBuilder.StandardizationHelper helper) {
        if (currentNode == null || currentNode.getData() == null) {
            throw new IllegalStateException("Infix function node is not properly linked to the AST.");
        }

        // Get the three children: left operand, function, right operand
        FCNSNode<ASTNode> leftOperand = currentNode.getFirstChild();
        FCNSNode<ASTNode> functionNode = (leftOperand != null) ? leftOperand.getNextSibling() : null;
        FCNSNode<ASTNode> rightOperand = (functionNode != null) ? functionNode.getNextSibling() : null;

        // Validate structure
        if (leftOperand == null || functionNode == null || rightOperand == null) {
            throw new IllegalStateException("Infix function must have pattern: E1 @ F E2");
        }
        if (rightOperand.getNextSibling() != null) {
            throw new IllegalStateException("Infix function can only have three components");
        }

        // Standardize all components
        FCNSNode<STNode> stLeft = helper.standardizeChild(leftOperand);
        FCNSNode<STNode> stFunction = helper.standardizeChild(functionNode);
        FCNSNode<STNode> stRight = helper.standardizeChild(rightOperand);

        // Build gamma (gamma F E1) E2 structure
        FCNSNode<STNode> innerGamma = new FCNSNode<>(new STGamma());
        innerGamma.setFirstChild(stFunction);
        stFunction.setNextSibling(stLeft);

        FCNSNode<STNode> outerGamma = new FCNSNode<>(new STGamma());
        outerGamma.setFirstChild(innerGamma);
        innerGamma.setNextSibling(stRight);

        return outerGamma;
    }
}