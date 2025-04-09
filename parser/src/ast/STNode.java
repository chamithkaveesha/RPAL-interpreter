package ast;

public class STNode extends Node{
    private String value;
    public STNode(String label) {
        super(label);
    }
    public STNode(String label, String value) {
        super(label);
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
