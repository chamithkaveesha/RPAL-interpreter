package ast.operators;

import ast.ASTNode;
import ast.STNode;

public class ASTInfixFunction extends ASTNode {
    public ASTInfixFunction() {
        super("@");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
