package ast;

public class STString extends STNode {
    private final String value;
    public STString(String value) {
        super("<STR:" + value + ">");
        this.value = value;
    }
}
