package tree.ast.definitions;

import tree.ast.ASTNode;
import tree.st.STNode;

public class ASTFunctionForm extends ASTNode {
    public ASTFunctionForm() {
        super("function_form");
    }

    @Override
    public STNode standardize() {
        return null;
    }
}
