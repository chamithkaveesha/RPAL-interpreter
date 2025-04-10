package tree.ast.ratorsandrands;

import tree.ast.ASTNode;
import tree.st.STBuilder;
import tree.st.terminals.STNil;
import tree.st.STNode;
import utils.FCNSNode;

public class ASTNil extends ASTNode {
    public ASTNil() {
        super("nil");
    }

    @Override
    public FCNSNode<STNode> standardize(STBuilder.StandardizationHelper helper) {
        return new FCNSNode<>(new STNil());
    }
}
