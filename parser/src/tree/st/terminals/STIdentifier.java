package tree.st.terminals;

import tree.st.STNode;

public class STIdentifier extends STNode {
    private final String name;
    public STIdentifier(String name) {
        super("<ID:" + name + ">");
        this.name = name;
    }
}
