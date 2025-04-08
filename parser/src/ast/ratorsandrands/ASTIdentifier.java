package ast.ratorsandrands;

import ast.ASTNode;
import ast.STNode;

public class ASTIdentifier extends ASTNode {
    public String name;
    public ASTIdentifier(String name) {
        super("<ID:" + name + ">");
        this.name = name;
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
