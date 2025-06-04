package tree.ast.expressions;

import tree.ast.ASTNode;
import tree.st.STBuilder;
import tree.st.STLambda;
import tree.st.STNode;
import utils.FCNSNode;

public class ASTLambda extends ASTNode {
    public ASTLambda() {
        super("lambda");
    }

    @Override
    public FCNSNode<STNode> standardize(FCNSNode<ASTNode> currentNode, STBuilder.StandardizationHelper helper) {
        if (currentNode == null || currentNode.getData() == null) {
            throw new IllegalStateException("Lambda node is not properly linked to the AST.");
        }

        FCNSNode<ASTNode> currentChild = currentNode.getFirstChild();
        if (currentChild == null) {
            throw new IllegalStateException("Lambda must have at least one bound variable and a body");
        }

        // Process variables and build nested lambdas
        FCNSNode<STNode> currentLambda = null;
        FCNSNode<STNode> firstLambda = null;

        while (currentChild.getNextSibling() != null) { // While there are more variables
            // Create new lambda node for this variable
            FCNSNode<STNode> newLambda = new FCNSNode<>(new STLambda());

            // Standardize the variable and set as first child
            FCNSNode<STNode> stVar = helper.standardizeChild(currentChild);
            newLambda.setFirstChild(stVar);

            if (currentLambda == null) {
                firstLambda = newLambda; // Remember the outermost lambda
            } else {
                // Connect previous lambda's body to this new lambda
                currentLambda.getFirstChild().setNextSibling(newLambda);
            }

            currentLambda = newLambda;
            currentChild = currentChild.getNextSibling();
        }

        // Standardize and attach the body to the innermost lambda
        FCNSNode<STNode> stBody = helper.standardizeChild(currentChild);
        if (currentLambda == null) {
            // Single variable case
            firstLambda = new FCNSNode<>(new STLambda());
            firstLambda.setFirstChild(helper.standardizeChild(currentNode.getFirstChild()));
            firstLambda.getFirstChild().setNextSibling(stBody);
        } else {
            currentLambda.getFirstChild().setNextSibling(stBody);
        }

        return firstLambda;
    }
}