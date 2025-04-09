package tree.ast.expressions;

import tree.ast.ASTNode;
import tree.st.STNode;

public class ASTLet extends ASTNode {
    public ASTLet() {
        super("let");
    }

    @Override
    public STNode standardize() {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
