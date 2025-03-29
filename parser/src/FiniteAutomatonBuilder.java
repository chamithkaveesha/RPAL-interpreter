import java.util.*;

public class FiniteAutomatonBuilder {
    private final Map<String, State> states = new HashMap<>();
    private State initialState;
    private final Set<State> acceptingStates = new HashSet<>();
    private final Set<State> outputStates = new HashSet<>();
    private final Set<Character> language = new HashSet<>();

    public State addState(String name) {
        State state = new State(name);
        states.put(name, state);
        return state;
    }

    public FiniteAutomatonBuilder setInitialState(String stateName) {
        initialState = states.get(stateName);
        if (initialState == null) {
            throw new IllegalArgumentException("State not found: " + stateName);
        }
        return this;
    }

    public FiniteAutomatonBuilder addAcceptingState(String stateName) {
        State state = states.get(stateName);
        if (state == null) {
            throw new IllegalArgumentException("State not found: " + stateName);
        }
        acceptingStates.add(state);
        return this;
    }

    public FiniteAutomatonBuilder addOutputState(String stateName) {
        State state = states.get(stateName);
        if (state == null) {
            throw new IllegalArgumentException("State not found: " + stateName);
        }
        outputStates.add(state);
        return this;
    }

    public FiniteAutomatonBuilder addTransition(String fromState, char symbol, String toState) {
        State source = states.get(fromState);
        State target = states.get(toState);
        if (source == null || target == null) {
            throw new IllegalArgumentException("Invalid state names");
        }
        source.setTransition(symbol, target);
        language.add(symbol);
        return this;
    }

    public FiniteAutomaton build() {
        if (initialState == null) {
            throw new IllegalStateException("Initial state not set");
        }
        return new FiniteAutomaton(
                new ArrayList<>(states.values()),
                initialState,
                acceptingStates,
                language
        );
    }
}