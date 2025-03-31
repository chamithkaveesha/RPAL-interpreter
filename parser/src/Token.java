public class Token {
    private TokenType type;
    private final String lexeme;
    private final int line;
    private final int column;

    public Token(TokenType type, String lexeme, int line, int column) {
        this.type = type;
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
    }

    public TokenType getType() {
        return type;
    }
    public void setType(TokenType type) {
        this.type = type;
    }
    public int getLine() {
        return line;
    }
    public int getColumn() {
        return column;
    }
    public String getLexeme() {
        return lexeme;
    }

    @Override
    public String toString() {
        return type + ": " + lexeme + " at line: " + line + " column: " + column;
    }
}
