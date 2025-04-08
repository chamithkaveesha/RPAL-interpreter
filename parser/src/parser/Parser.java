package parser;

import ast.Node;
import utils.FCNSTree;

public interface Parser {
    void parse();
    FCNSTree<Node> getAST();
}
