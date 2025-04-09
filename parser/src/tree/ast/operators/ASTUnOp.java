package tree.ast.operators;

import tree.ast.ASTNode;
import tree.st.STNode;

public class ASTUnOp extends ASTNode {
    public ASTUnOp(UnaryOperator operator) {
        super(operator.toString());
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
