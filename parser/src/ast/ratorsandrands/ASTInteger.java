package ast.ratorsandrands;

import ast.ASTNode;
import ast.STInteger;
import ast.STNode;

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
