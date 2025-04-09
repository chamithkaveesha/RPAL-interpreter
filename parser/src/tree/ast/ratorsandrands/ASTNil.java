package tree.ast.ratorsandrands;

import tree.ast.ASTNode;
import tree.st.STNil;
import tree.st.STNode;

public class ASTNil extends ASTNode {
    public ASTNil() {
        super("nil");
    }

    @Override
    public STNode standardize() {
        return new STNil();
    }
}
