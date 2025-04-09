package ast.tuples;

import ast.ASTNode;
import ast.STNode;

public class ASTTau extends ASTNode {
    public ASTTau() {
        super("tau");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
