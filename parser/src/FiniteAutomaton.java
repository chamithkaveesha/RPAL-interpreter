import java.util.*;

public class FiniteAutomaton {
    private final List<State> states;
    private Set<State> acceptingStates;
    private Set<State> outputStates;
    private Set<Character> language;
    private State initialState;
    private State currentState;


    FiniteAutomaton(List<State> states, State initialState,
                    Set<State> acceptingStates, Set<Character> language) {
        this.states = new ArrayList<>(states);
        this.initialState = initialState;
        this.currentState = initialState;
        this.acceptingStates = new HashSet<>(acceptingStates);
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
        return acceptingStates.contains(currentState);
    }
    public boolean isOutputState(){
        return outputStates.contains(currentState);
    }
}
