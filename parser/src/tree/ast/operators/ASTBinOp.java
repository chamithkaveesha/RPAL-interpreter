package tree.ast.operators;

import tree.ast.ASTNode;
import tree.st.STNode;

public class ASTBinOp extends ASTNode {
    public ASTBinOp(BinaryOperator operator) {
        super(operator.toString());
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
