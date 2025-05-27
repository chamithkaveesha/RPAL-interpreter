package tree.ast.ratorsandrands;

import tree.ast.ASTNode;
import tree.st.STBuilder;
import tree.st.STNode;
import tree.st.terminals.STString;
import utils.FCNSNode;

public class ASTString extends ASTNode {
    private final String value;

    public ASTString(String value) {
        super("<STR:" + stripQuotes(value) + ">");
        this.value = stripQuotes(value);
    }

    // FIXME: this logic doesn't belong here
    private static String stripQuotes(String raw) {
        if (raw != null && raw.length() >= 2 && raw.startsWith("'") && raw.endsWith("'")) {
            return raw.substring(1, raw.length() - 1);
        }
        return raw;
    }

    @Override
    public FCNSNode<STNode> standardize(STBuilder.StandardizationHelper helper) {
        return new FCNSNode<>(new STString(value));
    }
}
