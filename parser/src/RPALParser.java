import java.util.Iterator;
import java.util.List;

public class RPALParser implements Parser {
    private final Iterator<Token> tokenIterator;
    private TokenType nextToken;

    public RPALParser(List<Token> tokens) {
        this.tokenIterator = tokens.iterator();
        nextToken = getNextToken();
    }

    public void parse(List<Token> tokens) {
        E();
    }

    private void read(TokenType tokenType) {
        if (tokenType != nextToken) {
            throw new ParserException("Expected " + tokenType + " but got " + nextToken);
        }
        this.nextToken = getNextToken();
    }

    private TokenType getNextToken(){
        return tokenIterator.next().type();
    }

    // recursion
    private void E(){
        switch (nextToken){
            case KEYWORD_LET:
                read(TokenType.KEYWORD_LET);
                D();
                read(TokenType.KEYWORD_IN);
                E();
                break;
            case KEYWORD_FN:
                read(TokenType.KEYWORD_FN);
                Vb();
                // here use available symbols for Vb: is it not?
                while(nextToken == TokenType.IDENTIFIER || nextToken == TokenType.OPEN_BRACKET){
                    Vb();
                }
                read(TokenType.PERIOD);
                E();
                break;
            default:
                Ew();
                break;
        }
    }

    private void Ew(){
        T();
        if(nextToken == TokenType.KEYWORD_WHERE){
            read(TokenType.KEYWORD_WHERE);
            Dr();
        }
    }

    private void T(){
        Ta();
        while(nextToken == TokenType.COMMA){
            read(TokenType.COMMA);
            Ta();
        }
    }

    private void Ta(){
        Tc();
        while (nextToken == TokenType.KEYWORD_AUG){
            read(TokenType.KEYWORD_AUG);
            Tc();
        }
    }

    // recursion
    private void Tc(){
        B();
        if (nextToken == TokenType.CONDITION_SIGN){
            read(TokenType.CONDITION_SIGN);
            Tc();
            read(TokenType.VERTICAL_BAR);
            Tc();
        }
    }

    private void B(){
        Bt();
        while (nextToken == TokenType.OR){
            read(TokenType.OR);
            Bt();
        }
    }

    private void Bt(){
        Bs();
        while (nextToken == TokenType.AND){
            read(TokenType.AND);
            Bs();
        }
    }

    private void Bs(){
        if (nextToken == TokenType.NOT){
            read(TokenType.NOT);
        }
        Bp();
    }

    private void Bp(){
        A();
        // TODO: check if both of the ge and >= recognized
        switch (nextToken){
            case GREATER_THAN:
                read(TokenType.GREATER_THAN);
                A();
                break;
            case GREATER_THAN_EQUAL:
                read(TokenType.GREATER_THAN_EQUAL);
                A();
                break;
            case LESS_THAN:
                read(TokenType.LESS_THAN);
                A();
                break;
            case LESS_THAN_EQUAL:
                read(TokenType.LESS_THAN_EQUAL);
                A();
                break;
            case EQUAL:
                read(TokenType.EQUAL);
                A();
                break;
            case NOT_EQUAL:
                read(TokenType.NOT_EQUAL);
                A();
                break;
            default:
                break;
        }
    }

    // TODO: this violates associativity
    private void A(){
        if (nextToken == TokenType.PLUS){
            read(TokenType.PLUS);
            At();
        }
        else if (nextToken == TokenType.MINUS){
            read(TokenType.MINUS);
            At();
        }
        else {
            At();
            while (nextToken == TokenType.PLUS || nextToken == TokenType.MINUS){
                if (nextToken == TokenType.PLUS){
                    read(TokenType.PLUS);
                }
                else {
                    read(TokenType.MINUS);
                }
                At();
            }
        }
    }

    private void At(){
        Af();
        while (nextToken == TokenType.DIVIDE || nextToken == TokenType.MULTIPLY){
            if (nextToken == TokenType.DIVIDE){
                read(TokenType.DIVIDE);
            }
            else {
                read(TokenType.MULTIPLY);
            }
            Af();
        }
    }

    private void Af(){
        Ap();
        while (nextToken == TokenType.EXPONENT){
            read(TokenType.EXPONENT);
            Ap();
        }
    }

    private void Ap(){
        R();
        while (nextToken == TokenType.INFIX_FUNCTION){
            read(TokenType.INFIX_FUNCTION);
            read(TokenType.IDENTIFIER);
            R();
        }
    }

    // revise this
    private void R(){
        Rn();
        while(nextToken == TokenType.IDENTIFIER || nextToken == TokenType.INTEGER
                || nextToken == TokenType.STRING || nextToken == TokenType.BOOLEAN
                || nextToken ==TokenType.NIL || nextToken == TokenType.OPEN_BRACKET
                || nextToken == TokenType.DUMMY){
            Rn();
        }
    }

    private void Rn(){
        switch (nextToken){
            case IDENTIFIER:
                read(TokenType.IDENTIFIER);
                break;
            case INTEGER:
                read(TokenType.INTEGER);
                break;
            case STRING:
                read(TokenType.STRING);
                break;
            case BOOLEAN:
                read(TokenType.BOOLEAN);
                break;
            case NIL:
                read(TokenType.NIL);
                break;
            case OPEN_BRACKET:
                read(TokenType.OPEN_BRACKET);
                E();
                read(TokenType.CLOSE_BRACKET);
                break;
            case DUMMY:
                read(TokenType.DUMMY);
                break;
            default:
                break;
        }
    }

    private void D(){
        Da();
        while (nextToken == TokenType.KEYWORD_WITHIN){
            read(TokenType.KEYWORD_WITHIN);
            Da();
        }
    }

    // in grammar, it says one or more and, but is it okay? / anyway i didn't use that logic
    private void Da(){
        Dr();
        while (nextToken == TokenType.AND_SIMULTANEOUS_DEFINITION){
            read(TokenType.AND_SIMULTANEOUS_DEFINITION);
            Dr();
        }
    }

    // is this the way
    private void Dr(){
        if (nextToken == TokenType.REC){
            read(TokenType.REC);
        }
        Db();
    }

    private void Db(){
        switch (nextToken){
            case IDENTIFIER:
                read(TokenType.IDENTIFIER);
                do {
                    Vb();
                } while (nextToken != TokenType.EQUAL);
                break;
            case OPEN_BRACKET:
                read(TokenType.OPEN_BRACKET);
                D();
                read(TokenType.CLOSE_BRACKET);
                break;
            default:
                Vl();
                read(TokenType.EQUAL);
                E();
                break;
        }
    }
    private void Vb(){
        switch (nextToken){
            case IDENTIFIER:
                read(TokenType.IDENTIFIER);
                break;
            case OPEN_BRACKET:
                read(TokenType.OPEN_BRACKET);
                if (nextToken != TokenType.CLOSE_BRACKET){
                    Vl();
                }
                read(TokenType.CLOSE_BRACKET);
                break;
            default:
                break;
        }
    }

    private void Vl(){
        read(TokenType.IDENTIFIER);
        while (nextToken != TokenType.COMMA){
            read(TokenType.COMMA);
            read(TokenType.IDENTIFIER);
        }
    }
}

class ParserException extends RuntimeException {
    public ParserException(String message) {
        super(message);
    }
}