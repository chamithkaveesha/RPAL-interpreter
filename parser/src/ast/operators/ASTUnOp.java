package ast.operators;

import ast.ASTNode;
import ast.STNode;

public class ASTUnOp extends ASTNode {
    public ASTUnOp(UnaryOperator operator) {
        super(operator.toString());
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
