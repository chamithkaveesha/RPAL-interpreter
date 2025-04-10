package tree.ast;

import tree.Node;
import tree.st.STBuilder;
import tree.st.STNode;
import utils.FCNSNode;

public abstract class ASTNode extends Node {
    protected FCNSNode<ASTNode> treeNode;

    public ASTNode(String lexeme) {
        super(lexeme);
    }

    public void setTreeNode(FCNSNode<ASTNode> treeNode) {
        this.treeNode = treeNode;
    }

    public FCNSNode<ASTNode> getTreeNode() {
        return treeNode;
    }

    public abstract FCNSNode<STNode> standardize(STBuilder.StandardizationHelper helper);
}

