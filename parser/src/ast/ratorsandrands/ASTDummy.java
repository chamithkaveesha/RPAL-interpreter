package ast.ratorsandrands;

import ast.ASTNode;
import ast.STDummy;
import ast.STNode;

public class ASTDummy extends ASTNode {
    public ASTDummy() {
        super("dummy");
    }

    @Override
    public STNode standardize() {
        return new STDummy();
    }
}
