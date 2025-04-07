package ast;

public class ASTBuilderTest {
    public static void main(String[] args) {
        ASTBuilder astBuilder = new ASTBuilder();
        astBuilder.buildTree(new ASTNode("rightleaf"), 2);
        astBuilder.buildTree(new ASTNode("leftleaf"), 2);
        astBuilder.buildTree(new ASTNode("root"), 0);
        astBuilder.buildTree(new ASTNode("rr"), 0);
        astBuilder.buildTree(new ASTNode("rl"), 0);
        astBuilder.get().traversePreOrder();
    }
}
