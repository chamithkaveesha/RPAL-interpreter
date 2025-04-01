public class IntegerASTNODE extends ASTNode{
    public int value;
    public IntegerASTNODE(int value) {
        super("<INT:" + value + ">");
        this.value = value;
    }
}
