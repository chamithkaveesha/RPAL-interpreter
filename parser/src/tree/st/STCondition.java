package tree.st;

import cse_machine.elements.BetaControlElement;
import cse_machine.elements.DeltaControlElement;
import tree.transform.ControlStructureBuilderHelper;
import utils.FCNSNode;

public class STCondition extends STNode {
    public STCondition() {
        super("->");
    }

    @Override
    public void buildControlStructure(ControlStructureBuilderHelper helper) {
        FCNSNode<STNode> thisNode = this.getTreeNode();
        if (thisNode == null) {
            throw new IllegalStateException("STCondition node's tree node is not set");
        }

        FCNSNode<STNode> conditionNode = thisNode.getFirstChild();
        FCNSNode<STNode> trueBranchNode = (conditionNode != null) ? conditionNode.getNextSibling() : null;
        FCNSNode<STNode> falseBranchNode = (trueBranchNode != null) ? trueBranchNode.getNextSibling() : null;

        if (conditionNode == null || trueBranchNode == null || falseBranchNode == null) {
            throw new IllegalStateException("STCondition must have exactly three children: condition, true branch, and false branch");
        }

        int previousLevel = helper.getCurrentLevel();

        // 1. Add Delta for TRUE branch and create new level
        int trueLevel = helper.addNewLevel();
        helper.addControlElement(new DeltaControlElement(trueLevel));

        // Build control structure for TRUE branch in new level
        helper.setCurrentLevel(trueLevel);
        trueBranchNode.getData().buildControlStructure(helper);

        // Return to previous level
        helper.setCurrentLevel(previousLevel);

        // 2. Add Delta for FALSE branch and create new level
        int falseLevel = helper.addNewLevel();
        helper.addControlElement(new DeltaControlElement(falseLevel));

        // Build control structure for FALSE branch in new level
        helper.setCurrentLevel(falseLevel);
        falseBranchNode.getData().buildControlStructure(helper);

        // Return to previous level
        helper.setCurrentLevel(previousLevel);

        // 3. Add Beta control element
        helper.addControlElement(new BetaControlElement());

        // 4. Build control structure for condition in current level
        conditionNode.getData().buildControlStructure(helper);
    }
}
