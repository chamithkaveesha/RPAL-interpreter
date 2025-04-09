package ast.ratorsandrands;

import ast.ASTNode;
import ast.STNode;
import ast.STString;

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
