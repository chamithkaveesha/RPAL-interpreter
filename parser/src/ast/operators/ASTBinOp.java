package ast.operators;

import ast.ASTNode;
import ast.STNode;

public class ASTBinOp extends ASTNode {
    public ASTBinOp(BinaryOperator operator) {
        super(operator.toString());
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
