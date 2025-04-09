package tree.ast;

import tree.Node;
import tree.st.STNode;

public abstract class ASTNode extends Node {
    public ASTNode(String lexeme) {
        super(lexeme);
    }

    public abstract STNode standardize();
}

