package tree.ast.ratorsandrands;

import tree.ast.ASTNode;
import tree.st.STDummy;
import tree.st.STNode;

public class ASTDummy extends ASTNode {
    public ASTDummy() {
        super("dummy");
    }

    @Override
    public STNode standardize() {
        return new STDummy();
    }
}
