package tree.ast.operators;

import tree.ast.ASTNode;
import tree.st.STNode;

public class ASTInfixFunction extends ASTNode {
    public ASTInfixFunction() {
        super("@");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
