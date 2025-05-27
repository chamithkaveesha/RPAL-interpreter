package tree.ast.definitions;

import tree.ast.ASTNode;
import tree.st.*;
import utils.FCNSNode;

public class ASTWithin extends ASTNode {
    public ASTWithin() {
        super("within");
    }

    @Override
    public FCNSNode<STNode> standardize(STBuilder.StandardizationHelper helper) {
        // Standardize both children of the "within" node
        FCNSNode<ASTNode> astFirstAssign = getTreeNode().getFirstChild();
        FCNSNode<ASTNode> astSecondAssign = astFirstAssign.getNextSibling();

        FCNSNode<STNode> stdFirstAssign = helper.standardizeChild(astFirstAssign);
        FCNSNode<STNode> stdSecondAssign = helper.standardizeChild(astSecondAssign);

        // From stdFirstAssign: X1 = E1
        FCNSNode<STNode> stdX1 = stdFirstAssign.getFirstChild();
        FCNSNode<STNode> stdE1 = stdX1.getNextSibling();

        // From stdSecondAssign: X2 = E2
        FCNSNode<STNode> stdX2 = stdSecondAssign.getFirstChild();
        FCNSNode<STNode> stdE2 = stdX2.getNextSibling();

        // Construct: lambda X1 . E2
        FCNSNode<STNode> lambdaNode = new FCNSNode<>(new STLambda());
        lambdaNode.setFirstChild(stdX1);
        stdX1.setNextSibling(stdE2);

        // Construct: gamma (lambda X1 . E2) E1
        FCNSNode<STNode> gammaNode = new FCNSNode<>(new STGamma());
        gammaNode.setFirstChild(lambdaNode);
        lambdaNode.setNextSibling(stdE1);

        // Final node: X2 = gamma
        FCNSNode<STNode> assignNode = new FCNSNode<>(new STAssign());
        assignNode.setFirstChild(stdX2);
        stdX2.setNextSibling(gammaNode);

        return assignNode;    }
}
