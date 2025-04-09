package tree.ast.tuples;

import tree.ast.ASTNode;
import tree.st.STNode;

public class ASTCondition extends ASTNode {
    public ASTCondition() {
        super("->");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
