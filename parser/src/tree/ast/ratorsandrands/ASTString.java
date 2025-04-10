package tree.ast.ratorsandrands;

import tree.ast.ASTNode;
import tree.st.STBuilder;
import tree.st.STNode;
import tree.st.terminals.STString;
import utils.FCNSNode;

public class ASTString extends ASTNode {
    private final String value;
    public ASTString(String value) {
        super("<STR:" + value + ">");
        this.value = value;
    }

    @Override
    public FCNSNode<STNode> standardize(STBuilder.StandardizationHelper helper) {
        return new FCNSNode<>(new STString(value));
    }
}
