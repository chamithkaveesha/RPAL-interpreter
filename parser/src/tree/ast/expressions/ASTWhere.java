package tree.ast.expressions;

import tree.ast.ASTNode;
import tree.st.STNode;

public class ASTWhere extends ASTNode {
    public ASTWhere() {
        super("where");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
