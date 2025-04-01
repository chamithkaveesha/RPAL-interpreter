import utils.FCNSNode;
import utils.FCNSTree;

import java.util.Stack;

public class ASTBuilder {
    private final Stack<FCNSTree<Token>> stack;

    public ASTBuilder() {
        this.stack = new Stack<>();
    }

    // is the token best class for this?
    public void buildTree(Token token, int childrenCount) {
        FCNSTree<Token> newTree = new FCNSTree<>(token);
        try {
            FCNSTree<Token> current;
            for (int i = 0; i < childrenCount; i++) {
                current = stack.pop();
                newTree.addChild(current);
            }
            stack.push(newTree);
        }
        catch (Exception e) {
            System.out.println("Error building tree");
        }
    }

    public FCNSTree<Token> get() {
        return stack.peek();
    }
}
