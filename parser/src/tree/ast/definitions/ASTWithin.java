package tree.ast.definitions;

import standardizer.STBuilder;
import tree.ast.ASTNode;
import tree.st.*;
import tree.st.nonterminals.STAssign;
import tree.st.nonterminals.STGamma;
import tree.st.nonterminals.STLambda;
import utils.FCNSNode;

public class ASTWithin extends ASTNode {
    public ASTWithin() {
        super("within");
    }

    @Override
    public FCNSNode<STNode> standardize(FCNSNode<ASTNode> currentNode, STBuilder.StandardizationHelper helper) {
        if (currentNode == null || currentNode.getData() == null) {
            throw new IllegalStateException("Within node is not properly linked to the AST.");
        }
        // Standardize both children of the "within" node
        FCNSNode<ASTNode> astFirstAssign = currentNode.getFirstChild();
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
