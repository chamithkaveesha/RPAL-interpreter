package tree.ast.ratorsandrands;

import tree.ast.ASTNode;
import tree.st.STNode;
import tree.st.STString;

public class ASTString extends ASTNode {
    private final String value;
    public ASTString(String value) {
        super("<STR:" + value + ">");
        this.value = value;
    }

    @Override
    public STNode standardize() {
        return new STString(value);
    }
}
