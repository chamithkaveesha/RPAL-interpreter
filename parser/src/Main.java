import java.util.*;

public class Main {
    public static void main(String[] args) {
        FiniteAutomaton automaton = makeAutomaton();
        HashMap<State, TokenType> acceptingStatesToTokenTypes = new HashMap<>();
        acceptingStatesToTokenTypes.put(automaton.getState("q0"), TokenType.IDENTIFIER);
        acceptingStatesToTokenTypes.put(automaton.getState("q1"), TokenType.IDENTIFIER);
        Scanner scanner = new Scanner(automaton, new HashMap<>(), acceptingStatesToTokenTypes);
        scanner.setInput("let f x y = x\n" +
                "in Print(f 3+4 (1//0))\n");
        try {
            for (int i = 0; i < 30; i++) {
                System.out.println("token" + i + " " + scanner.nextToken().getLexeme());
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

        FiniteAutomaton automaton = FiniteAutomatonBuilder.builder()
                .withStates(List.of(q0, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13))
                .withInitialState(q0.getName())
                .withAcceptingStates(List.of(q1, q2, q3, q5, q6, q7, q9, q10, q11, q12, q13))
                .withLanguage(language)
                .build();

        return automaton;
    }
}