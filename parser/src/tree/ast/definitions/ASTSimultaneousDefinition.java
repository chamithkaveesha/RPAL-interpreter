package tree.ast.definitions;

import tree.ast.ASTNode;
import tree.st.STNode;

public class ASTSimultaneousDefinition extends ASTNode {
    public ASTSimultaneousDefinition() {
        super("and");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
