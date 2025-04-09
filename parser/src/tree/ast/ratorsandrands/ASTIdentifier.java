package tree.ast.ratorsandrands;

import tree.ast.ASTNode;
import tree.st.STIdentifier;
import tree.st.STNode;

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
