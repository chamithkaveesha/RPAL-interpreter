package tree.ast.definitions;

import tree.ast.ASTNode;
import tree.st.STNode;

public class ASTAssign extends ASTNode {
    public ASTAssign() {
        super("=");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
