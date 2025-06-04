package tree.ast;

import tree.Node;
import standardizer.STBuilder;
import tree.st.STNode;
import utils.FCNSNode;

/**
 * Abstract base class for all AST nodes.
 * Provides common null checks and delegates actual standardization to subclasses.
 */
public abstract class ASTNode extends Node {

    /**
     * Constructs an AST node with the given lexeme.
     *
     * @param lexeme the token or fragment represented by this node
     */
    public ASTNode(String lexeme) {
        super(lexeme);
    }

    /**
     * Template method that performs common validation and then calls
     * {@link #doStandardize(FCNSNode, STBuilder.StandardizationHelper)} implemented by subclasses.
     *
     * @param currentNode the current AST node wrapper in the FCNS representation
     * @param helper utility class for standardization context
     * @return the standardized ST subtree
     * @throws IllegalArgumentException if currentNode or its data is null
     */
    public final FCNSNode<STNode> standardize(
            FCNSNode<ASTNode> currentNode,
            STBuilder.StandardizationHelper helper) {
        if (currentNode == null || currentNode.getData() == null) {
            throw new IllegalArgumentException("AST node is not properly linked or data is missing.");
        }
        return doStandardize(currentNode, helper);
    }

    /**
     * Actual standardization logic to be implemented by subclasses.
     * This method assumes input validation has been done.
     *
     * @param currentNode the current AST node wrapper
     * @param helper utility class for standardization context
     * @return the standardized ST subtree
     */
    protected abstract FCNSNode<STNode> doStandardize(
            FCNSNode<ASTNode> currentNode,
            STBuilder.StandardizationHelper helper);
}
