package tree.ast.variables;

import tree.ast.ASTNode;
import tree.st.STNode;

public class ASTEmpty extends ASTNode {
    public ASTEmpty() {
        super("()");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
