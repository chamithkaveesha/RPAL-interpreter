package tree.ast.tuples;

import tree.ast.ASTNode;
import tree.st.STNode;

public class ASTAug extends ASTNode {
    public ASTAug() {
        super("aug");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
