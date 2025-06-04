package tree.ast.tuples;

import tree.ast.ASTNode;
import standardizer.STBuilder;
import tree.st.nonterminals.STCondition;
import tree.st.STNode;
import utils.FCNSNode;

public class ASTCondition extends ASTNode {
    public ASTCondition() {
        super("->");
    }

    @Override
    public FCNSNode<STNode> doStandardize(FCNSNode<ASTNode> currentNode, STBuilder.StandardizationHelper helper) {
        // Get the three children: condition, true-branch, false-branch
        FCNSNode<ASTNode> condition = currentNode.getFirstChild();
        FCNSNode<ASTNode> trueBranch = (condition != null) ? condition.getNextSibling() : null;
        FCNSNode<ASTNode> falseBranch = (trueBranch != null) ? trueBranch.getNextSibling() : null;

        // Standardize all children
        FCNSNode<STNode> stCondition = (condition != null) ? helper.standardizeChild(condition) : null;
        FCNSNode<STNode> stTrue = (trueBranch != null) ? helper.standardizeChild(trueBranch) : null;
        FCNSNode<STNode> stFalse = (falseBranch != null) ? helper.standardizeChild(falseBranch) : null;

        // Create the standardized ST node (same operator)
        FCNSNode<STNode> stNode = new FCNSNode<>(new STCondition());

        // Rebuild the structure with standardized children
        if (stCondition != null) {
            stNode.setFirstChild(stCondition);
            if (stTrue != null) {
                stCondition.setNextSibling(stTrue);
                if (stFalse != null) {
                    stTrue.setNextSibling(stFalse);
                }
            }
        }

        return stNode;
    }
}
