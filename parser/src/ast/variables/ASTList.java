package ast.variables;

import ast.ASTNode;
import ast.STNode;

public class ASTList extends ASTNode {
    public ASTList() {
        super(",");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
