package ast;

public class StringASTNode extends ASTNode {
    public String value;
    public StringASTNode(String value) {
        super("<STR:" + value + ">");
        this.value = value;
    }
}
