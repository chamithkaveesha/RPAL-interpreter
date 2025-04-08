package ast.expressions;

import ast.ASTNode;
import ast.STNode;

public class ASTLambda extends ASTNode {
    public ASTLambda() {
        super("lambda");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
