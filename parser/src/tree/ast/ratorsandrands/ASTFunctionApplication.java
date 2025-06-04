package tree.ast.ratorsandrands;

import tree.ast.ASTNode;
import standardizer.STBuilder;
import tree.st.nonterminals.STGamma;
import tree.st.STNode;
import utils.FCNSNode;

public class ASTFunctionApplication extends ASTNode {
    public ASTFunctionApplication() {
        super("gamma");
    }


    @Override
    public FCNSNode<STNode> doStandardize(FCNSNode<ASTNode> currentNode, STBuilder.StandardizationHelper helper) {
        if (currentNode == null || currentNode.getData() == null) {
            throw new IllegalStateException("Gamma node is not properly linked to the AST.");
        }

        FCNSNode<STNode> stGamma = new FCNSNode<>(new STGamma());

        FCNSNode<ASTNode> astChild = currentNode.getFirstChild();
        if (astChild != null) {
            // First child: function
            FCNSNode<STNode> stFunction = helper.standardizeChild(astChild);
            stGamma.setFirstChild(stFunction);

            // Second child: argument (if exists)
            FCNSNode<ASTNode> astArg = astChild.getNextSibling();
            if (astArg != null) {
                FCNSNode<STNode> stArg = helper.standardizeChild(astArg);
                stFunction.setNextSibling(stArg);
            }
        }

        return stGamma;
    }
}
