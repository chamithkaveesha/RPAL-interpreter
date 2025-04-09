package st;

import ast.Node;
import utils.FCNSTree;

public interface Standardizer {
    FCNSTree<Node> getST(FCNSTree<Node> tree);
}
