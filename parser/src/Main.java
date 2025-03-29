import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        FiniteAutomaton automaton = makeAutomaton();
        System.out.println(automaton);
        HashMap<State, TokenType> acceptingStatesToTokenTypes = new HashMap<>();
        acceptingStatesToTokenTypes.put(automaton.getState("q0"), TokenType.IDENTIFIER);
        acceptingStatesToTokenTypes.put(automaton.getState("q1"), TokenType.IDENTIFIER);
        Scanner scanner = new Scanner(automaton, new HashMap<>(), acceptingStatesToTokenTypes);
        scanner.setInput("vsdsdvasdv sdafa");
        System.out.println(scanner.nextToken().getLexeme());
    }

    private static FiniteAutomaton makeAutomaton() {
        State q0 = new State("q0");
        State q1 = new State("q1");
        State q2 = new State("q2");
        List<Character> letters = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            letters.add(c);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            letters.add(c);
        }
        List<Character> digits = new ArrayList<>();
        for (char c = '0'; c <= '9'; c++) {
            digits.add(c);
        }
        q0.setTransition(letters, q1);
        q1.setTransition(letters, q2);
        q1.setTransition(digits, q2);
        q2.setTransition(letters, q2);
        q2.setTransition(digits, q2);

        Set<Character> language = new HashSet<>();
        language.addAll(letters);
        language.addAll(digits);

        FiniteAutomaton automaton = FiniteAutomatonBuilder.builder()
                .withStates(List.of(q0, q1, q2))
                .withInitialState(q0.getName())
                .withAcceptingState(q2.getName())
                .withAcceptingState(q1.getName())
                .withLanguage(language)
                .build();

        return automaton;
    }
}