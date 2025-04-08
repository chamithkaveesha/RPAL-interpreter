package ast.ratorsandrands;

import ast.ASTNode;
import ast.STNode;

public class ASTBoolean extends ASTNode {
    private final boolean value;
    public ASTBoolean(Boolean value) {
        super(value != null ? value.toString() : throwIllegalArgumentException());
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    private static String throwIllegalArgumentException() {
        throw new IllegalArgumentException("value cannot be null");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
