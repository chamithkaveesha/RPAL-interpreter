package tree.ast.definitions;

import tree.ast.ASTNode;
import tree.st.STNode;

public class ASTRecursiveFunction extends ASTNode {
    public ASTRecursiveFunction() {
        super("rec");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
