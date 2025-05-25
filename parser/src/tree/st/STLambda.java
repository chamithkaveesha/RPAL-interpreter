package tree.st;

import cse_machine.elements.control.LambdaControlElement;
import tree.st.terminals.STIdentifier;
import tree.transform.ControlStructureBuilderHelper;
import utils.FCNSNode;

public class STLambda extends STNode {
    public STLambda() {
        super("lambda");
    }

    @Override
    public void buildControlStructure(ControlStructureBuilderHelper helper) {
        FCNSNode<STNode> thisNode = this.getTreeNode();
        if (thisNode == null) {
            throw new IllegalStateException("STLambda node's tree node is not set");
        }

        // Assume children order: parameters node, body node
        FCNSNode<STNode> parametersNode = thisNode.getFirstChild();
        FCNSNode<STNode> bodyNode = (parametersNode != null) ? parametersNode.getNextSibling() : null;

        if (parametersNode == null || bodyNode == null) {
            throw new IllegalStateException("STLambda must have exactly two children: parameters and body");
        }

        STNode node = parametersNode.getData();
        if (!(node instanceof STIdentifier)){
            throw new IllegalStateException("STLambda's parameters node expected to be STIdentifier but was " + node.getClass().getSimpleName());
        }

        String boundVariable = ((STIdentifier) node).getName();

        // To add new control structure for lambda
        int previousLevel = helper.getCurrentLevel();

        int newLevel = helper.addNewLevel();

        // Add Lambda control element representing lambda abstraction
        helper.addControlElement(new LambdaControlElement(boundVariable, newLevel));

        helper.setCurrentLevel(newLevel);

        // Build control structure for the body of the lambda in new level
        bodyNode.getData().buildControlStructure(helper);

        helper.setCurrentLevel(previousLevel);
    }
}
