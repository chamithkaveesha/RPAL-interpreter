package tree.st;

import cse_machine.elements.BinOpControlElement;
import tree.transform.ControlStructureBuilderHelper;
import utils.FCNSNode;

public class STAug extends STNode {
    public STAug() {
        super("aug");
    }

    @Override
    public void buildControlStructure(ControlStructureBuilderHelper helper) {
        FCNSNode<STNode> thisNode = this.getTreeNode();
        if (thisNode == null) {
            throw new IllegalStateException("STAug node's tree node is not set");
        }

        FCNSNode<STNode> leftNode = thisNode.getFirstChild();
        FCNSNode<STNode> rightNode = (leftNode != null) ? leftNode.getNextSibling() : null;

        if (leftNode == null || rightNode == null) {
            throw new IllegalStateException("STAug must have exactly two children: left and right operands");
        }

        // Add binary operation control element for 'aug'
        helper.addControlElement(new BinOpControlElement("aug"));

        // Build control structure for left operand
        leftNode.getData().buildControlStructure(helper);

        // Build control structure for right operand
        rightNode.getData().buildControlStructure(helper);
    }
}
