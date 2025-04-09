package tree.ast.variables;

import tree.ast.ASTNode;
import tree.st.STNode;

public class ASTList extends ASTNode {
    public ASTList() {
        super(",");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
