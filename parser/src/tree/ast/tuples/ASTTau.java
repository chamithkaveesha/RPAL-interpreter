package tree.ast.tuples;

import tree.ast.ASTNode;
import tree.st.STNode;

public class ASTTau extends ASTNode {
    public ASTTau() {
        super("tau");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
