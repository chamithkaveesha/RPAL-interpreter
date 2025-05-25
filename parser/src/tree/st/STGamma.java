package tree.st;

import cse_machine.elements.control.GammaControlElement;
import tree.transform.ControlStructureBuilderHelper;
import utils.FCNSNode;

public class STGamma extends STNode {
    public STGamma() {
        super("gamma");
    }

    @Override
    public void buildControlStructure(ControlStructureBuilderHelper helper) {
        FCNSNode<STNode> thisNode = this.getTreeNode();
        if (thisNode == null) {
            throw new IllegalStateException("STGamma node's tree node is not set");
        }

        FCNSNode<STNode> functionNode = thisNode.getFirstChild();
        FCNSNode<STNode> argumentNode = (functionNode != null) ? functionNode.getNextSibling() : null;

        if (functionNode == null || argumentNode == null) {
            throw new IllegalStateException("STGamma must have exactly two children: function and argument");
        }

        // Add Gamma control element representing function application
        helper.addControlElement(new GammaControlElement());

        // Build control structure for the function part
        functionNode.getData().buildControlStructure(helper);

        // Build control structure for the argument part
        argumentNode.getData().buildControlStructure(helper);
    }
}
