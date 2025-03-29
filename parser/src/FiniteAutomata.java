import java.util.*;

public class FiniteAutomata {
    private final List<State> states;
    private Set<State> finalStates;
    private Set<Character> language;
    private State initialState;
    private State currentState;


    FiniteAutomata(List<State> states, State initialState,
                           Set<State> finalStates, Set<Character> language) {
        this.states = new ArrayList<>(states);
        this.initialState = initialState;
        this.currentState = initialState;
        this.finalStates = new HashSet<>(finalStates);
        this.language = new HashSet<>(language);
    }

    public State transition(char symbol) {
        if (!language.contains(symbol)) {
            throw new IllegalArgumentException("Symbol not in language: " + symbol);
        }
        State nextState = currentState.getTransition(symbol);
        if (nextState == null) {
            throw new IllegalStateException("No transition for symbol: " + symbol);
        }
        if (!states.contains(nextState)) {
            throw new IllegalStateException(
                    "Transition leads to invalid state: " + nextState.getName() +
                            ". State is not part of the automaton."
            );
        }
        currentState = nextState;
        return currentState;
    }

    public void reset() {
        currentState = initialState;
    }

    public State getCurrentState(){
        return this.currentState;
    }
    public boolean isAcceptingState(){
        return finalStates.contains(currentState);
    }
}
