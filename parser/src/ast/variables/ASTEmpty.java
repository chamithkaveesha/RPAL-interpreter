package ast.variables;

import ast.ASTNode;
import ast.STNode;

public class ASTEmpty extends ASTNode {
    public ASTEmpty() {
        super("()");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
