public class ASTBuilderTest {
    public static void main(String[] args) {
        ASTBuilder astBuilder = new ASTBuilder();
        astBuilder.buildTree(new Token(TokenType.EOF, "eof", 2,2), 0);
        astBuilder.buildTree(new Token(TokenType.EOF, "eof", 1,2), 0);
        astBuilder.buildTree(new Token(TokenType.EOF, "eof", 1,1), 0);
        astBuilder.buildTree(new Token(TokenType.EOF, "eof", 2,1), 2);
        astBuilder.buildTree(new Token(TokenType.EOF, "eof", 3,3), 2);
        astBuilder.get().traversePreOrder();
    }
}
