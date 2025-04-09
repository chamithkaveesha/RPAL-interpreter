package parser;

import tree.ast.ASTNode;
import utils.FCNSTree;

public interface Parser {
    void parse();
    FCNSTree<ASTNode> getAST();
}
