package tree.st;

import cse_machine.elements.control.UnOpControlElement;
import tree.transform.ControlStructureBuilderHelper;
import utils.FCNSNode;

public class STUnOp extends STNode {
    public STUnOp(String label) {
        super(label);
    }

    @Override
    public void buildControlStructure(ControlStructureBuilderHelper helper) {
        FCNSNode<STNode> thisNode = this.getTreeNode();
        if (thisNode == null) {
            throw new IllegalStateException("STUnOp node's tree node is not set");
        }

        FCNSNode<STNode> operandNode = thisNode.getFirstChild();
        if (operandNode == null) {
            throw new IllegalStateException("STUnOp must have exactly one child operand");
        }

        // Then add the unary operator element itself
        helper.addControlElement(new UnOpControlElement(this.getLabel()));

        // Build control structure for operand first
        operandNode.getData().buildControlStructure(helper);
    }
}
