import java.util.*;

public final class FiniteAutomatonBuilder {
    private final Map<String, State> states = new HashMap<>();
    private State initialState;
    private final Set<State> acceptingStates = new HashSet<>();
    private final Set<State> outputStates = new HashSet<>();
    private final Set<Character> language = new HashSet<>();

    private FiniteAutomatonBuilder() {}

    public static FiniteAutomatonBuilder builder() {
        return new FiniteAutomatonBuilder();
    }

    public FiniteAutomatonBuilder withState(String name) {
        if (!states.containsKey(name)) {
            states.put(name, new State(name));
        }
        return this;
    }

    public FiniteAutomatonBuilder withStates(List<State> statesList) {
        for (State state : statesList) {
            states.put(state.getName(), state);
        }
        return this;
    }

    public FiniteAutomatonBuilder withInitialState(String stateName) {
        this.initialState = states.computeIfAbsent(stateName, State::new);
        return this;
    }

    public FiniteAutomatonBuilder withLanguage(Set<Character> language) {
        this.language.addAll(language);
        return this;
    }

    public FiniteAutomatonBuilder withAcceptingState(String stateName) {
        State state = states.computeIfAbsent(stateName, State::new);
        this.acceptingStates.add(state);
        return this;
    }

    public FiniteAutomatonBuilder withAcceptingStates(List<State> statesList) {
        acceptingStates.addAll(statesList);
        return this;
    }

    public FiniteAutomatonBuilder withOutputState(String stateName) {
        State state = states.computeIfAbsent(stateName, State::new);
        this.outputStates.add(state);
        return this;
    }

    public FiniteAutomatonBuilder withTransition(String fromState, char symbol, String toState) {
        State source = states.computeIfAbsent(fromState, State::new);
        State target = states.computeIfAbsent(toState, State::new);
        source.setTransition(symbol, target);
        language.add(symbol);
        return this;
    }

    public FiniteAutomaton build() {
        if (initialState == null) {
            throw new IllegalStateException("Initial state must be specified");
        }
        if (states.isEmpty()) {
            throw new IllegalStateException("At least one state must be defined");
        }

        return new FiniteAutomaton(
                new ArrayList<>(states.values()),
                initialState,
                acceptingStates,
                language
        );
    }
}