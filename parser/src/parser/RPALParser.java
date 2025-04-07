package parser;

import ast.*;
import scanner.Token;
import scanner.TokenType;
import utils.FCNSTree;

import java.util.Iterator;
import java.util.List;

public class RPALParser implements Parser {
    private final Iterator<Token> tokenIterator;
    private Token nextToken;
    private TokenType nextTokenType;
    private final ASTBuilder astBuilder;

    public RPALParser(List<Token> tokens, ASTBuilder astBuilder) {
        this.tokenIterator = tokens.iterator();
        nextTokenType = getNextTokenType();
        this.astBuilder = astBuilder;
    }

    @Override
    public void parse() {
            E();
    }

    @Override
    public FCNSTree<ASTNode> getAST() {
        return astBuilder.get();
    }

    private void read(TokenType tokenType) {
        if (tokenType != nextTokenType) {
            throw new ParserException("Expected " + tokenType + " but got " + nextTokenType);
        }
        switch (nextTokenType) {
            case IDENTIFIER:
                astBuilder.buildTree(new IdentifierASTNode(nextToken.lexeme()), 0);
                break;
            case INTEGER:
                astBuilder.buildTree(new IntegerASTNODE(Integer.parseInt(nextToken.lexeme())), 0);
                break;
            case STRING:
                astBuilder.buildTree(new StringASTNode(nextToken.lexeme()), 0);
                break;
        }
        this.nextTokenType = getNextTokenType();
    }

    private TokenType getNextTokenType(){
        nextToken = tokenIterator.next();
        return nextToken.type();
    }

    // recursion
    private void E(){
        switch (nextTokenType){
            case KEYWORD_LET:
                read(TokenType.KEYWORD_LET);
                D();
                read(TokenType.KEYWORD_IN);
                E();
                astBuilder.buildTreeOrdered(new ASTNode("let"), 2);
                break;
            case KEYWORD_FN:
                read(TokenType.KEYWORD_FN);
                int n = 1;
                Vb();
                // here use available symbols for Vb: is it not?
                // in ast, as it seems lambda goes for more than two symbols, function definition
                while(nextTokenType == TokenType.IDENTIFIER || nextTokenType == TokenType.OPEN_BRACKET){
                    Vb();
                    n++;
                }
                read(TokenType.PERIOD);
                E();
                astBuilder.buildTreeOrdered(new ASTNode("lambda"), n + 1);
                break;
            default:
                Ew();
                break;
        }
    }

    private void Ew(){
        T();
        if(nextTokenType == TokenType.KEYWORD_WHERE){
            read(TokenType.KEYWORD_WHERE);
            Dr();
            astBuilder.buildTreeOrdered(new ASTNode("where"), 2);
        }
    }

    private void T(){
        Ta();
        int n = 1;
        while(nextTokenType == TokenType.COMMA){
            read(TokenType.COMMA);
            Ta();
            n++;
        }
        // TODO: is there nice logic than this
        if (n > 1){
            astBuilder.buildTreeOrdered(new ASTNode("tau"), n);
        }
    }

    // aug is a binary node, so need a different way of managing
    // TODO: check whether this builds the correct tree
    private void Ta(){
        Tc();
        while (nextTokenType == TokenType.KEYWORD_AUG){
            read(TokenType.KEYWORD_AUG);
            Tc();
            astBuilder.buildTreeOrdered(new ASTNode("aug"), 2);
        }
    }

    // recursion
    private void Tc(){
        B();
        if (nextTokenType == TokenType.CONDITION_SIGN){
            read(TokenType.CONDITION_SIGN);
            Tc();
            read(TokenType.VERTICAL_BAR);
            Tc();
            astBuilder.buildTreeOrdered(new ASTNode("->"), 3);
        }
    }

    // TODO: check this
    private void B(){
        Bt();
        while (nextTokenType == TokenType.OR){
            read(TokenType.OR);
            Bt();
            astBuilder.buildTreeOrdered(new ASTNode("or"), 2);
        }
    }

    private void Bt(){
        Bs();
        while (nextTokenType == TokenType.AND){
            read(TokenType.AND);
            Bs();
            astBuilder.buildTreeOrdered(new ASTNode("&"), 2);
        }
    }

    // here different because not should be a parent
    private void Bs(){
        if (nextTokenType == TokenType.NOT){
            read(TokenType.NOT);
            Bp();
            astBuilder.buildTree(new ASTNode("not"), 1);
        }
        else {
            Bp();
        }
    }

    private void Bp(){
        A();
        // TODO: check if both of the ge and >= recognized
        switch (nextTokenType){
            case GREATER_THAN:
                read(TokenType.GREATER_THAN);
                A();
                astBuilder.buildTreeOrdered(new ASTNode("gr"), 2);
                break;
            case GREATER_THAN_EQUAL:
                read(TokenType.GREATER_THAN_EQUAL);
                A();
                astBuilder.buildTreeOrdered(new ASTNode("ge"), 2);
                break;
            case LESS_THAN:
                read(TokenType.LESS_THAN);
                A();
                astBuilder.buildTreeOrdered(new ASTNode("ls"), 2);
                break;
            case LESS_THAN_EQUAL:
                read(TokenType.LESS_THAN_EQUAL);
                A();
                astBuilder.buildTreeOrdered(new ASTNode("le"), 2);
                break;
            case EQUAL:
                read(TokenType.EQUAL);
                A();
                astBuilder.buildTreeOrdered(new ASTNode("eq"), 2);
                break;
            case NOT_EQUAL:
                read(TokenType.NOT_EQUAL);
                A();
                astBuilder.buildTreeOrdered(new ASTNode("ne"), 2);
                break;
            default:
                break;
        }
    }

    // TODO: this violates associativity
    private void A(){
        if (nextTokenType == TokenType.PLUS){
            read(TokenType.PLUS);
            At();
        }
        // TODO: check for different orders of signs
        else if (nextTokenType == TokenType.MINUS){
            read(TokenType.MINUS);
            At();
            astBuilder.buildTree(new ASTNode("neg"), 1);
        }
        // TODO: check if correct tree get built
        else {
            At();
        }
        while (nextTokenType == TokenType.PLUS || nextTokenType == TokenType.MINUS){
            if (nextTokenType == TokenType.PLUS){
                read(TokenType.PLUS);
                At();
                astBuilder.buildTreeOrdered(new ASTNode("+"), 2);
            }
            else {
                read(TokenType.MINUS);
                At();
                astBuilder.buildTreeOrdered(new ASTNode("-"), 2);
            }
        }
    }

    private void At(){
        Af();
        while (nextTokenType == TokenType.DIVIDE || nextTokenType == TokenType.MULTIPLY){
            if (nextTokenType == TokenType.DIVIDE){
                read(TokenType.DIVIDE);
                Af();
                astBuilder.buildTreeOrdered(new ASTNode("/"), 2);
            }
            else {
                read(TokenType.MULTIPLY);
                Af();
                astBuilder.buildTreeOrdered(new ASTNode("*"), 2);
            }
        }
    }

    private void Af(){
        Ap();
        if (nextTokenType == TokenType.EXPONENT){
            read(TokenType.EXPONENT);
            Af();
            astBuilder.buildTreeOrdered(new ASTNode("**"), 2);
        }
    }

    // TODO: check associativity
    private void Ap(){
        R();
        while (nextTokenType == TokenType.INFIX_FUNCTION){
            read(TokenType.INFIX_FUNCTION);
            read(TokenType.IDENTIFIER);
            R();
            astBuilder.buildTreeOrdered(new ASTNode("@"), 3);
        }
    }

    // revise this
    private void R(){
        Rn();
        while(nextTokenType == TokenType.IDENTIFIER || nextTokenType == TokenType.INTEGER
                || nextTokenType == TokenType.STRING || nextTokenType == TokenType.BOOLEAN
                || nextTokenType ==TokenType.NIL || nextTokenType == TokenType.OPEN_BRACKET
                || nextTokenType == TokenType.DUMMY){
            Rn();
            astBuilder.buildTreeOrdered(new ASTNode("gamma"), 2);
        }
    }

    private void Rn(){
        switch (nextTokenType){
            case IDENTIFIER:
                read(TokenType.IDENTIFIER);
                break;
            case INTEGER:
                read(TokenType.INTEGER);
                break;
            case STRING:
                read(TokenType.STRING);
                break;
            // TODO: manage true false differently?
            case BOOLEAN:
                read(TokenType.BOOLEAN);
                astBuilder.buildTree(new ASTNode("<BOOLEAN:>"), 0);
                break;
            case NIL:
                read(TokenType.NIL);
                astBuilder.buildTree(new ASTNode("nil"), 0);
                break;
            case OPEN_BRACKET:
                read(TokenType.OPEN_BRACKET);
                E();
                read(TokenType.CLOSE_BRACKET);
                break;
            case DUMMY:
                read(TokenType.DUMMY);
                astBuilder.buildTree(new ASTNode("dummy"), 0);
                break;
            default:
                break;
        }
    }

    // TODO: associativity, double check
    // this should not work when two withins are there
    private void D(){
        Da();
        if (nextTokenType == TokenType.KEYWORD_WITHIN){
            read(TokenType.KEYWORD_WITHIN);
            D();
            astBuilder.buildTreeOrdered(new ASTNode("within"), 2);
        }
    }

    private void Da(){
        Dr();
        int n = 1;
        while (nextTokenType == TokenType.AND_SIMULTANEOUS_DEFINITION){
            read(TokenType.AND_SIMULTANEOUS_DEFINITION);
            Dr();
            n++;
        }
        if (n > 1){
            astBuilder.buildTreeOrdered(new ASTNode("and"), n);
        }
    }

    // is this the way
    private void Dr(){
        if (nextTokenType == TokenType.REC){
            read(TokenType.REC);
            Db();
            astBuilder.buildTree(new ASTNode("rec"), 1);
        }
        else {
            Db();
        }
    }

    // TODO: verify this
    private void Db(){
        switch (nextTokenType){
            // 2 steps look ahead needed here
            case IDENTIFIER:
                read(TokenType.IDENTIFIER);
                int n = 1;
                if (nextTokenType == TokenType.IDENTIFIER || nextTokenType == TokenType.OPEN_BRACKET){
                    int VbCount = 0;
                    do {
                        Vb();
                        VbCount++;
                    } while (nextTokenType != TokenType.EQUAL);
                    read(TokenType.EQUAL);
                    E();
                    astBuilder.buildTreeOrdered(new ASTNode("function_form"), n + VbCount + 1);
                    break;
                }
                if (nextTokenType == TokenType.EQUAL){
                    read(TokenType.EQUAL);
                    E();
                    astBuilder.buildTreeOrdered(new ASTNode("="), 2);
                    break;
                }
                if (nextTokenType == TokenType.COMMA){
                    do {
                        read(TokenType.COMMA);
                        read(TokenType.IDENTIFIER);
                        n++;
                    } while (nextTokenType == TokenType.COMMA);
                    E();
                    astBuilder.buildTreeOrdered(new ASTNode("="), n + 1);
                }
                break;
            case OPEN_BRACKET:
                read(TokenType.OPEN_BRACKET);
                D();
                read(TokenType.CLOSE_BRACKET);
                break;
            default:
                break;
        }
    }

    private void Vb(){
        switch (nextTokenType){
            case IDENTIFIER:
                read(TokenType.IDENTIFIER);
                break;
            case OPEN_BRACKET:
                read(TokenType.OPEN_BRACKET);
                if (nextTokenType == TokenType.CLOSE_BRACKET){
                    read(TokenType.CLOSE_BRACKET);
                    // terminal node
                    astBuilder.buildTree(new ASTNode("()"), 0);
                    break;
                }
                Vl();
                read(TokenType.CLOSE_BRACKET);
                break;
            default:
                break;
        }
    }

    private void Vl(){
        read(TokenType.IDENTIFIER);
        int n = 1;
        while (nextTokenType == TokenType.COMMA){
            read(TokenType.COMMA);
            read(TokenType.IDENTIFIER);
            n++;
        }
        astBuilder.buildTreeOrdered(new ASTNode(","), n);
    }
}

class ParserException extends RuntimeException {
    public ParserException(String message) {
        super(message);
    }
}