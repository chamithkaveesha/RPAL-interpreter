package tree.ast.operators;

import tree.ast.ASTNode;
import tree.st.nonterminals.STBinOp;
import standardizer.STBuilder;
import tree.st.STNode;
import utils.FCNSNode;

public class ASTBinOp extends ASTNode {
    public ASTBinOp(BinaryOperator operator) {
        super(operator.toString());
    }

    @Override
    public FCNSNode<STNode> standardize(FCNSNode<ASTNode> currentNode, STBuilder.StandardizationHelper helper) {
        if (currentNode == null || currentNode.getData() == null) {
            throw new IllegalStateException("Binary operator node is not properly linked to the AST.");
        }

        // Get left and right operands from the AST
        FCNSNode<ASTNode> leftOperand = currentNode.getFirstChild();
        FCNSNode<ASTNode> rightOperand = (leftOperand != null) ? leftOperand.getNextSibling() : null;

        // Standardize the operands (if they exist)
        FCNSNode<STNode> stLeft = (leftOperand != null) ? helper.standardizeChild(leftOperand) : null;
        FCNSNode<STNode> stRight = (rightOperand != null) ? helper.standardizeChild(rightOperand) : null;

        // Create the standardized ST node (same operator)
        FCNSNode<STNode> stNode = new FCNSNode<>(new STBinOp(getLabel()));

        // Attach standardized children (preserving original structure)
        if (stLeft != null) {
            stNode.setFirstChild(stLeft);
            if (stRight != null) {
                stLeft.setNextSibling(stRight);
            }
        }

        return stNode;
    }
}
