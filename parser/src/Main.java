import java.io.*;
import java.util.*;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

public class Main {
    public static void main(String[] args) {

        String filename = getFilenameFromCommandLineArguments(args);
        if (filename == null) return;

        String fileContent;
        try {
            fileContent = new String(readAllBytes(get(filename)));
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + filename);
            return;
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return;
        }

        Scanner scanner = setupScanner();

        scanner.setInput(fileContent);
        try {
            for (Token token : scanner.tokenize()) {
                System.out.println(token);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getFilenameFromCommandLineArguments(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Main <filename>");
            return null;
        }
        return args[0];
    }

    private static Scanner setupScanner() {
        FiniteAutomaton automaton = makeAutomaton();
        HashMap<State, TokenType> acceptingStatesToTokenTypes = setupStateTokenTypeHashMap(automaton);
        return new Scanner(automaton, new HashMap<>(), acceptingStatesToTokenTypes);
    }

    private static HashMap<State, TokenType> setupStateTokenTypeHashMap(FiniteAutomaton automaton) {
        HashMap<State, TokenType> acceptingStatesToTokenTypes = new HashMap<>();
        acceptingStatesToTokenTypes.put(automaton.getState("q1"), TokenType.IDENTIFIER);
        acceptingStatesToTokenTypes.put(automaton.getState("q2"), TokenType.INTEGER);
        acceptingStatesToTokenTypes.put(automaton.getState("q3"), TokenType.OPERATOR);
        acceptingStatesToTokenTypes.put(automaton.getState("q5"), TokenType.STRING);
        acceptingStatesToTokenTypes.put(automaton.getState("q6"), TokenType.DELETE);
        // / - recognized as operator
        acceptingStatesToTokenTypes.put(automaton.getState("q7"), TokenType.OPERATOR);
        // //+ - recognized as comments
        acceptingStatesToTokenTypes.put(automaton.getState("q9"), TokenType.DELETE);
        acceptingStatesToTokenTypes.put(automaton.getState("q10"), TokenType.PUNCTUATION_OPEN_BRACKET);
        acceptingStatesToTokenTypes.put(automaton.getState("q11"), TokenType.PUNCTUATION_CLOSE_BRACKET);
        acceptingStatesToTokenTypes.put(automaton.getState("q12"), TokenType.PUNCTUATION_SEMICOLON);
        acceptingStatesToTokenTypes.put(automaton.getState("q13"), TokenType.PUNCTUATION_COMMA);
        return acceptingStatesToTokenTypes;
    }

    private static FiniteAutomaton makeAutomaton() {
        State q0 = new State("q0");
        State q1 = new State("q1");
        State q2 = new State("q2");
        State q3 = new State("q3");
        State q4 = new State("q4");
        State q5 = new State("q5");
        State q6 = new State("q6");
        State q7 = new State("q7");
        State q8 = new State("q8");
        State q9 = new State("q9");
        State q10 = new State("q10");
        State q11 = new State("q11");
        State q12 = new State("q12");
        State q13 = new State("q13");

        List<Character> letters = SymbolUtils.getLetters();
        List<Character> digits = SymbolUtils.getDigits();
        List<Character> operatorSymbols = SymbolUtils.getOperatorSymbols();

        // Identifiers
        q0.setTransition(letters, q1);
        q1.setTransition(letters, q1);
        q1.setTransition(digits, q1);
        q1.setTransition('_', q1);

        // Integers
        q0.setTransition(digits, q2);
        q2.setTransition(digits, q2);

        // Operator Symbols
        q0.setTransition(operatorSymbols, q3);
        q3.setTransition(operatorSymbols, q3);
        // because comments and divide symbol ambiguity, will split divide symbol

        // Strings
        q0.setTransition('"', q4);

        List<Character> stringSymbols = SymbolUtils.getStringSymbols();
        q4.setTransition(letters, q4);
        q4.setTransition(digits, q4);
        q4.setTransition(operatorSymbols, q4);
        q4.setTransition(stringSymbols, q4);

        q4.setTransition('"', q5);

        // Spaces
        q0.setTransition(' ', q6);
        q0.setTransition('\t', q6);
        q0.setTransition('\n', q6);
        // TODO: is /r need to handle
        q6.setTransition(' ', q6);
        q6.setTransition('\t', q6);
        q6.setTransition('\n', q6);

        // Comments
        q0.setTransition('/', q7);
        q7.setTransition('/', q8);

        List<Character> commentSymbols = SymbolUtils.getCommentSymbols();
        q8.setTransition(letters, q8);
        q8.setTransition(digits, q8);
        q8.setTransition(operatorSymbols, q8);
        q8.setTransition(commentSymbols, q8);
        // more / in comment
        q8.setTransition('/', q8);
        // FIXME: keep this as this is or remove final \n
        q8.setTransition('\n', q9);

        // Punction
        q0.setTransition('(', q10);
        q0.setTransition(')', q11);
        q0.setTransition(';', q12);
        q0.setTransition(',', q13);

        Set<Character> language = new HashSet<>();
        language.addAll(letters);
        language.addAll(digits);
        language.addAll(operatorSymbols);
        language.add('/');
        language.addAll(stringSymbols);
        language.addAll(commentSymbols);        // these contain all other possible symbols also

        return FiniteAutomatonBuilder.builder()
                .withStates(List.of(q0, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13))
                .withInitialState(q0.getName())
                .withAcceptingStates(List.of(q1, q2, q3, q5, q6, q7, q9, q10, q11, q12, q13))
                .withLanguage(language)
                .build();
    }
}