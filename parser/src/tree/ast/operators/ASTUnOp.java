package tree.ast.operators;

import tree.ast.ASTNode;
import tree.st.STBuilder;
import tree.st.STNode;
import tree.st.STUnOp;
import utils.FCNSNode;

public class ASTUnOp extends ASTNode {
    public ASTUnOp(UnaryOperator operator) {
        super(operator.toString());
    }

    @Override
    public FCNSNode<STNode> standardize(STBuilder.StandardizationHelper helper) {
        if (getTreeNode() == null) {
            throw new IllegalStateException("Unary operator node is not properly linked to the AST.");
        }

        // Get the operand (unary operators have exactly one child)
        FCNSNode<ASTNode> astOperand = getTreeNode().getFirstChild();

        // Standardize the operand
        FCNSNode<STNode> stOperand = (astOperand != null) ? helper.standardizeChild(astOperand) : null;

        // Create the standardized ST node (same operator)
        FCNSNode<STNode> stNode = new FCNSNode<>(new STUnOp(getLabel()));

        // Attach the standardized operand
        if (stOperand != null) {
            stNode.setFirstChild(stOperand);
        }

        return stNode;
    }
}
