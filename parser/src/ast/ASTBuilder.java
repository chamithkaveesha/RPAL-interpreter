package ast;

import utils.FCNSTree;
import java.util.Stack;

public class ASTBuilder {
    private final Stack<FCNSTree<ASTNode>> stack;
    private final Stack<FCNSTree<ASTNode>> inverseStack;

    public ASTBuilder() {
        this.stack = new Stack<>();
        this.inverseStack = new Stack<>();
    }

    public void buildTree(ASTNode token, int childrenCount) {
        FCNSTree<ASTNode> newTree = new FCNSTree<>(token);
        try {
            for (int i = 0; i < childrenCount; i++) {
                newTree.addChild(stack.pop());
            }
            stack.push(newTree);
        } catch (Exception e) {
            System.out.println("Error building tree: " + e.getMessage());
        }
    }

    // gives elements as they were from top of the stack
    // uses secondary stack to manage that
    public void buildTreeOrdered(ASTNode token, int childrenCount) {
        for (int i = 0; i < childrenCount; i++) {
            if (!stack.isEmpty()) {
                inverseStack.push(stack.pop());
            }
        }

        FCNSTree<ASTNode> newTree = new FCNSTree<>(token);

        while (!inverseStack.isEmpty()) {
            newTree.addChild(inverseStack.pop());
        }

        stack.push(newTree);
    }

    public FCNSTree<ASTNode> get() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.peek();
    }

    public void clear() {
        stack.clear();
        inverseStack.clear();
    }
}