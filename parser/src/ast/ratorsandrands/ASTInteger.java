package ast.ratorsandrands;

import ast.ASTNode;
import ast.STNode;

public class ASTInteger extends ASTNode {
    public int value;
    public ASTInteger(int value) {
        super("<INT:" + value + ">");
        this.value = value;
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
