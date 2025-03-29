import java.util.*;

public class Scanner {
    private final FiniteAutomaton lexerAutomaton;
    private final Map<String, TokenType> keywords;
    private final Map<State, TokenType> acceptingStatesToTokenTypes;
    private String input;
    private int currentPosition;
    private int lineNumber;
    private int columnNumber;

    public Scanner(FiniteAutomaton lexerAutomaton, Map<String, TokenType> keywords,
                   Map<State, TokenType> acceptingStatesToTokenTypes) {
        this.lexerAutomaton = lexerAutomaton;
        this.keywords = new HashMap<>(keywords);
        this.acceptingStatesToTokenTypes = new HashMap<>(acceptingStatesToTokenTypes);
        reset();
    }

    public void setInput(String input) {
        this.input = input;
        reset();
    }

    private void reset() {
        this.currentPosition = 0;
        this.lineNumber = 1;
        this.columnNumber = 1;
        lexerAutomaton.reset();
    }

    public Token nextToken() {
        if (currentPosition >= input.length()) {
            return new Token(TokenType.EOF, "", lineNumber, columnNumber);
        }

        int startPosition = currentPosition;
        int startLine = lineNumber;
        int startColumn = columnNumber;
        StringBuilder lexeme = new StringBuilder();

        lexerAutomaton.reset();

        while (currentPosition < input.length()) {
            char currentChar = input.charAt(currentPosition);

            try {
                if (lexerAutomaton.isOutputState()) {
                    String tokenText = lexeme.toString();
                    return new Token(
                            acceptingStatesToTokenTypes.get(lexerAutomaton.getCurrentState()),
                            tokenText,
                            startLine,
                            startColumn
                    );
                }

                // lookahead for next state belonging to sama token class
                // if not output the token
                if (!lexerAutomaton.hasTransition(currentChar)) {
                    break;
                }

                // Process the character
                lexerAutomaton.transition(currentChar);
                lexeme.append(currentChar);
                updatePosition(currentChar);
                currentPosition++;
            } catch (IllegalArgumentException | IllegalStateException e) {
                break;
            }
        }

        // output the valid token
        if (lexerAutomaton.isAcceptingState()) {
            String tokenText = lexeme.toString();

            TokenType tokenType = acceptingStatesToTokenTypes.get(lexerAutomaton.getCurrentState());
            // TODO: move this to scanner or keep here
            if (tokenType == TokenType.IDENTIFIER && keywords.containsKey(tokenText)) {
                return new Token(keywords.get(tokenText), tokenText, startLine, startColumn);
            }

            return new Token(tokenType, tokenText, startLine, startColumn);
        }

        String invalidText = input.substring(startPosition, Math.min(startPosition + 10, input.length()));
        throw new ScannerException("Invalid token at line " + startLine + ", column " + startColumn +
                ": '" + invalidText + "'");
    }

    private void updatePosition(char c) {
        if (c == '\n') {
            lineNumber++;
            columnNumber = 1;
        } else {
            columnNumber++;
        }
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        Token token;
        do {
            token = nextToken();
            tokens.add(token);
        } while (token.getType() != TokenType.EOF);
        return tokens;
    }
}

class ScannerException extends RuntimeException {
    public ScannerException(String message) {
        super(message);
    }
}