package ast.definitions;

import ast.ASTNode;
import ast.STNode;

public class ASTRecursiveFunction extends ASTNode {
    public ASTRecursiveFunction() {
        super("rec");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
