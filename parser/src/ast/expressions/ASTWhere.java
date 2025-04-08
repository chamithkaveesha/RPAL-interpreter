package ast.expressions;

import ast.ASTNode;
import ast.STNode;

public class ASTWhere extends ASTNode {
    public ASTWhere() {
        super("where");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
