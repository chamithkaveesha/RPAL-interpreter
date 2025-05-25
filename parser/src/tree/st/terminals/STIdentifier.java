package tree.st.terminals;

import cse_machine.elements.control.IdentifierControlElement;
import tree.st.STNode;
import tree.transform.ControlStructureBuilderHelper;

public class STIdentifier extends STNode {
    private final String name;
    public STIdentifier(String name) {
        super("<ID:" + name + ">");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void buildControlStructure(ControlStructureBuilderHelper helper) {
        helper.addControlElement(new IdentifierControlElement(name));
    }
}
