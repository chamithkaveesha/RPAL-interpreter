import java.util.HashMap;
import java.util.List;

public class State {
    private final String name;
    private HashMap<Character, State> transitions;

    public State(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public State getTransition(char c) {
        return transitions.get(c);
    }
    public void setTransition(char c, State s) {
        transitions.put(c, s);
    }
    public void setTransition(List<Character> inputs, State s) {
        for (char c : inputs) {
            setTransition(c, s);
        }
    }
}
