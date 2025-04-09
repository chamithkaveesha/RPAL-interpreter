package ast.ratorsandrands;

import ast.ASTNode;
import ast.STIdentifier;
import ast.STNode;

public class ASTIdentifier extends ASTNode {
    private final String name;
    public ASTIdentifier(String name) {
        super("<ID:" + name + ">");
        this.name = name;
    }

    @Override
    public STNode standardize() {
        return new STIdentifier(name);
    }
}
