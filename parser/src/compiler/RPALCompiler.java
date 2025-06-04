package compiler;

import cse_machine.Control;
import cse_machine.CseMachine;
import cse_machine.Environment;
import cse_machine.Stack;
import parser.Parser;
import parser.RPALParser;
import scanner.*;
import standardizer.RPALStandardizer;
import parser.ASTBuilder;
import tree.ast.ASTNode;
import tree.st.STNode;
import tree.transform.ControlStructure;
import tree.transform.ControlStructureBuilder;
import utils.FCNSNode;

import java.util.List;

public class RPALCompiler {
    private final String source;

    private List<Token> tokens;
    private FCNSNode<ASTNode> ast;
    private FCNSNode<STNode> st;
    private List<ControlStructure> controlStructures;
    private CseMachine cseMachine;

    public RPALCompiler(String source) {
        this.source = source;
    }

    public void compile() {
        tokenize();
        parse();
        standardize();
        buildControlStructures();
        setupCseMachine();
    }

    private void tokenize() {
        Scanner scanner = new RPALScanner();
        scanner.setInput(source);
        tokens = new RPALScreener().screen(scanner.tokenize());
    }

    private void parse() {
        ASTBuilder builder = new ASTBuilder();
        Parser parser = new RPALParser(tokens, builder);
        parser.parse();
        ast = parser.getAST();
    }

    private void standardize() {
        st = new RPALStandardizer().getST(ast);
    }

    private void buildControlStructures() {
        ControlStructureBuilder builder = new ControlStructureBuilder();
        controlStructures = builder.build(st);
    }

    private void setupCseMachine() {
        Control control = new Control(controlStructures);
        Stack stack = new Stack();
        Environment primitiveEnvironment = PrimitiveEnvironmentFactory.create();
        cseMachine = new CseMachine(control, stack, primitiveEnvironment);
    }

    // Public accessors
    public FCNSNode<ASTNode> getAST() {
        return ast;
    }

    public FCNSNode<STNode> getST() {
        return st;
    }

    public List<ControlStructure> getControlStructures() {
        return controlStructures;
    }

    public CseMachine getCseMachine() {
        return cseMachine;
    }
}
