package tree.st;

import tree.Node;
import tree.transform.ControlStructureBuilderHelper;
import utils.FCNSNode;

public abstract class STNode extends Node {
    protected FCNSNode<STNode> treeNode;

    public STNode(String label) {
        super(label);
    }

    public void setTreeNode(FCNSNode<STNode> treeNode) {
        this.treeNode = treeNode;
    }

    public FCNSNode<STNode> getTreeNode() {
        return treeNode;
    }

    public abstract void buildControlStructure(ControlStructureBuilderHelper helper);
}
