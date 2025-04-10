package tree.st.terminals;

import tree.st.STNode;

public class STBoolean extends STNode {
    private final boolean value;
    public STBoolean(Boolean value) {
        super(value != null ? "<" + value.toString() + ">" : throwIllegalArgumentException());
        this.value = value;
    }

    private boolean getValue() {
        return value;
    }

    private static String throwIllegalArgumentException() {
        throw new IllegalArgumentException("value cannot be null");
    }
}
