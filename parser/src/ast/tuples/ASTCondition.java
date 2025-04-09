package ast.tuples;

import ast.ASTNode;
import ast.STNode;

public class ASTCondition extends ASTNode {
    public ASTCondition() {
        super("->");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
