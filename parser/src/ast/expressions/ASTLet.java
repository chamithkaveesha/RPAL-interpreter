package ast.expressions;

import ast.ASTNode;
import ast.STNode;

public class ASTLet extends ASTNode {
    public ASTLet() {
        super("let");
    }

    @Override
    public STNode standardize() {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
