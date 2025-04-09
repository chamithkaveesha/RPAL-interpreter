package tree.ast.expressions;

import tree.ast.ASTNode;
import tree.st.STNode;

public class ASTLambda extends ASTNode {
    public ASTLambda() {
        super("lambda");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
