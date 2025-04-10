package tree.ast.ratorsandrands;

import tree.ast.ASTNode;
import tree.st.STBoolean;
import tree.st.STNode;

public class ASTBoolean extends ASTNode {
    private final boolean value;
    public ASTBoolean(Boolean value) {
        super(value != null ? "<" + value.toString() + ">" : throwIllegalArgumentException());
        this.value = value;
    }

    private static String throwIllegalArgumentException() {
        throw new IllegalArgumentException("value cannot be null");
    }

    @Override
    public STNode standardize() {
        return new STBoolean(value);
    }
}
