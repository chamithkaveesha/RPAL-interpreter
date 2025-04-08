package ast;

import ast.operators.ASTBinOp;
import ast.operators.BinaryOperator;
import ast.ratorsandrands.ASTInteger;

public class ASTBuilderTest {
    public static void main(String[] args) {
        ASTBuilder astBuilder = new ASTBuilder();
        astBuilder.buildTree(new ASTBinOp(BinaryOperator.ADD), 2);
        astBuilder.buildTree(new ASTBinOp(BinaryOperator.MULTIPLY), 2);
        astBuilder.buildTree(new ASTInteger(1), 0);
        astBuilder.buildTree(new ASTInteger(2), 0);
        astBuilder.buildTree(new ASTInteger(3), 0);
        astBuilder.get().traversePreOrder();
    }
}
