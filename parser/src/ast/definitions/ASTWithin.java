package ast.definitions;

import ast.ASTNode;
import ast.STNode;

public class ASTWithin extends ASTNode {
    public ASTWithin() {
        super("within");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
