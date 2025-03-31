import java.util.ArrayList;
import java.util.List;

public class RPALScreener implements Screener {
    @Override
    public List<Token> screen(List<Token> tokens) {
        List<Token> out = new ArrayList<>();
        for (Token token : tokens) {
            if (token.getType() != TokenType.DELETE) {
                out.add(token);
            }
        }
        if (tokens.get(tokens.size() - 1).getType() == TokenType.DELETE) {
            Token last = tokens.get(tokens.size() - 1);
            out.add(new Token(TokenType.EOF, "EOF", last.getLine(), last.getColumn()));
        }
        return out;
    }
}
