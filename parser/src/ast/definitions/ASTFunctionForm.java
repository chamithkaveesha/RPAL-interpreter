package ast.definitions;

import ast.ASTNode;
import ast.STNode;

public class ASTFunctionForm extends ASTNode {
    public ASTFunctionForm() {
        super("function_form");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
