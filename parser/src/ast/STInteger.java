package ast;

public class STInteger extends STNode {
    private final int value;
    public STInteger(int value) {
        super("<INT:" + value + ">");
        this.value = value;
    }
}
