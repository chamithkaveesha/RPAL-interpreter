package ast.ratorsandrands;

import ast.ASTNode;
import ast.STNode;

public class ASTString extends ASTNode {
    public String value;
    public ASTString(String value) {
        super("<STR:" + value + ">");
        this.value = value;
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
