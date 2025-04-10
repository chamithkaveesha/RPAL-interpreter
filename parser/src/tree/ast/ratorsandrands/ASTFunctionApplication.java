package tree.ast.ratorsandrands;

import tree.ast.ASTNode;
import tree.st.STBuilder;
import tree.st.STGamma;
import tree.st.STNode;
import utils.FCNSNode;

public class ASTFunctionApplication extends ASTNode {
    public ASTFunctionApplication() {
        super("gamma");
    }


    @Override
    public FCNSNode<STNode> standardize(STBuilder.StandardizationHelper helper) {
        FCNSNode<STNode> stGamma = new FCNSNode<>(new STGamma());

        if (treeNode != null) {
            FCNSNode<ASTNode> astChild = treeNode.getFirstChild();
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
        }

        return stGamma;
    }
}
