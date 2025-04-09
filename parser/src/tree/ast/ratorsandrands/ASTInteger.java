package tree.ast.ratorsandrands;

import tree.ast.ASTNode;
import tree.st.STInteger;
import tree.st.STNode;

public class ASTInteger extends ASTNode {
    private final int value;
    public ASTInteger(int value) {
        super("<INT:" + value + ">");
        this.value = value;
    }

    @Override
    public STNode standardize() {
        return new STInteger(value);
    }
}
