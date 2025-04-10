package tree.ast.variables;

import tree.ast.ASTNode;
import tree.st.STBuilder;
import tree.st.STNode;
import utils.FCNSNode;

public class ASTList extends ASTNode {
    public ASTList() {
        super(",");
    }

    @Override
    public FCNSNode<STNode> standardize(STBuilder.StandardizationHelper helper) {
        return null;
    }
}
