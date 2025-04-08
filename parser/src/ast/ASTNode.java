package ast;

public abstract class ASTNode extends Node {
    public ASTNode(String lexeme) {
        super(lexeme);
    }

    public abstract STNode standardize();
}

