package ast.definitions;

import ast.ASTNode;
import ast.STNode;

public class ASTAssign extends ASTNode {
    public ASTAssign() {
        super("=");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
