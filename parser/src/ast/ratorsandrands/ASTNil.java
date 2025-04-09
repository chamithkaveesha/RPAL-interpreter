package ast.ratorsandrands;

import ast.ASTNode;
import ast.STNil;
import ast.STNode;

public class ASTNil extends ASTNode {
    public ASTNil() {
        super("nil");
    }

    @Override
    public STNode standardize() {
        return new STNil();
    }
}
