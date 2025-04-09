package tree.ast.definitions;

import tree.ast.ASTNode;
import tree.st.STNode;

public class ASTWithin extends ASTNode {
    public ASTWithin() {
        super("within");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
