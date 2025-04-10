package tree.ast.definitions;

import tree.ast.ASTNode;
import tree.st.STBuilder;
import tree.st.STNode;
import tree.st.STAssign;
import tree.st.STLambda;
import utils.FCNSNode;
import java.util.Stack;

public class ASTFunctionForm extends ASTNode {
    public ASTFunctionForm() {
        super("fcn_form");
    }

    @Override
    public FCNSNode<STNode> standardize(STBuilder.StandardizationHelper helper) {
        if (getTreeNode() == null) {
            throw new IllegalStateException("Function form node is not properly linked to the AST.");
        }

        // Get the function name (first child)
        FCNSNode<ASTNode> functionNameNode = getTreeNode().getFirstChild();
        if (functionNameNode == null) {
            throw new IllegalStateException("Function must have a name");
        }

        // Collect parameters (middle children) and body (last child)
        FCNSNode<ASTNode> current = functionNameNode.getNextSibling();
        if (current == null) {
            throw new IllegalStateException("Function must have a body");
        }

        // Use a stack to maintain parameter order
        Stack<FCNSNode<ASTNode>> paramStack = new Stack<>();
        FCNSNode<ASTNode> bodyNode = current;

        // Push all parameters to stack (except last node which is body)
        while (current.getNextSibling() != null) {
            paramStack.push(current);
            current = current.getNextSibling();
        }
        bodyNode = current; // Last node is the body

        // Standardize the body first
        FCNSNode<STNode> stBody = helper.standardizeChild(bodyNode);

        // Build lambdas from left to right using the stack
        FCNSNode<STNode> currentLambda = stBody;
        while (!paramStack.empty()) {
            FCNSNode<ASTNode> param = paramStack.pop();
            FCNSNode<STNode> stParam = helper.standardizeChild(param);
            FCNSNode<STNode> newLambda = new FCNSNode<>(new STLambda());
            newLambda.setFirstChild(stParam);
            stParam.setNextSibling(currentLambda);
            currentLambda = newLambda;
        }

        // Create the assignment node
        FCNSNode<STNode> stAssign = new FCNSNode<>(new STAssign());
        FCNSNode<STNode> stFunctionName = helper.standardizeChild(functionNameNode);

        stAssign.setFirstChild(stFunctionName);
        stFunctionName.setNextSibling(currentLambda);

        return stAssign;
    }
}