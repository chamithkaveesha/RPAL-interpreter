package ast;

public class ASTNode {
    public String lexeme;
    public ASTNode(String lexeme) {
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return lexeme;
    }
}

