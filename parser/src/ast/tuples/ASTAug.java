package ast.tuples;

import ast.ASTNode;
import ast.STNode;

public class ASTAug extends ASTNode {
    public ASTAug() {
        super("aug");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
