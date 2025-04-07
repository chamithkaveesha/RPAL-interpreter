package ast;

public class IdentifierASTNode extends ASTNode {
    public String name;
    public IdentifierASTNode(String name) {
        super("<ID:" + name + ">");
        this.name = name;
    }
}
