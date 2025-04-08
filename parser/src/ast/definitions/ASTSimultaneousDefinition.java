package ast.definitions;

import ast.ASTNode;
import ast.STNode;

public class ASTSimultaneousDefinition extends ASTNode {
    public ASTSimultaneousDefinition() {
        super("and");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
