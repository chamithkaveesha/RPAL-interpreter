import cse_machine.Control;
import cse_machine.CseMachine;
import cse_machine.Environment;
import cse_machine.Stack;
import cse_machine.elements.control.ControlElement;
import cse_machine.elements.stack.BinOpStackElement;
import cse_machine.elements.stack.UnOpStackElement;
import cse_machine.elements.stack.CurriedOperationStackElement;
import cse_machine.operations.binary.*;
import cse_machine.operations.two_step.ConcatenationOperation;
import cse_machine.operations.unary.*;
import st.RPALStandardizer;
import tree.ast.ASTBuilder;
import tree.ast.ASTNode;
import parser.Parser;
import parser.RPALParser;
import scanner.*;
import scanner.Scanner;
import tree.st.STNode;
import tree.transform.ControlStructure;
import tree.transform.ControlStructureBuilder;
import utils.FCNSNode;

import java.io.*;
import java.util.*;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

public class Main {
    public static void main(String[] args) {

        String filename = getFilenameFromCommandLineArguments(args);
        if (filename == null) return;

        String fileContent;
        try {
            fileContent = new String(readAllBytes(get(filename)));
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + filename);
            return;
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return;
        }

        Scanner scanner = new RPALScanner();

        scanner.setInput(fileContent);
        List<Token> tokenList = scanner.tokenize();
        Screener screener = new RPALScreener();
        List<Token> screenedTokens = screener.screen(tokenList);
        ASTBuilder astBuilder = new ASTBuilder();
        Parser parser = new RPALParser(screenedTokens, astBuilder);
        parser.parse();
        FCNSNode<ASTNode> ast = parser.getAST();
        System.out.println(ast.toString());
        FCNSNode<STNode> st = new RPALStandardizer().getST(ast);
        System.out.println(st);
        ControlStructureBuilder controlStructureBuilder = new ControlStructureBuilder();
        List<ControlStructure> controlStructureList = controlStructureBuilder.build(st);
        System.out.println(controlStructureList);

        Control control = new Control(controlStructureList);
        Stack stack = new Stack();
        Environment primitiveEnv = new Environment();
        primitiveEnv.setVariable("x", 1);
        primitiveEnv.setVariable("+", new BinOpStackElement("+", new IntAddition()));
        primitiveEnv.setVariable("-", new BinOpStackElement("-", new IntSubtraction()));
        primitiveEnv.setVariable("*", new BinOpStackElement("+", new IntMultiplication()));
        primitiveEnv.setVariable("/", new BinOpStackElement("/", new IntDivision()));
        primitiveEnv.setVariable("**", new BinOpStackElement("*", new IntExponentiation()));
        primitiveEnv.setVariable("ls", new BinOpStackElement("ls", new IntLessThan()));
        primitiveEnv.setVariable("&", new BinOpStackElement("&", new AndOperation()));
        primitiveEnv.setVariable("or", new BinOpStackElement("or", new AndOperation()));
        primitiveEnv.setVariable("Isinteger", new UnOpStackElement("Isinteger", new IsIntegerOperation()));
        primitiveEnv.setVariable("Istruthvalue", new UnOpStackElement("Istruthvalue", new IsTruthValueOperation()));
        primitiveEnv.setVariable("Isstring", new UnOpStackElement("Isstring", new IsStringOperation()));
        primitiveEnv.setVariable("Istuple", new UnOpStackElement("Istuple", new IsTupleOperation()));
        primitiveEnv.setVariable("Isdummy", new UnOpStackElement("Isdummy", new IsDummyOperation()));
        primitiveEnv.setVariable("gr", new BinOpStackElement("gr", new IntGreaterThan()));
        primitiveEnv.setVariable("Conc", new CurriedOperationStackElement<>(new ConcatenationOperation()));
        primitiveEnv.setVariable("eq", new BinOpStackElement("eq", new EqualOperation()));
        primitiveEnv.setVariable("ne", new BinOpStackElement("ne", new NotEqualOperation()));
        primitiveEnv.setVariable("neg", new UnOpStackElement("neg", new NegateOperation()));
        primitiveEnv.setVariable("not", new UnOpStackElement("not", new NotOperation()));
        CseMachine cseMachine = new CseMachine(control, stack, primitiveEnv);
        System.out.println(cseMachine);

        while (control.hasNext()) {
            System.out.println("--------------------------------------------------------");
            ControlElement element = control.next();
            element.accept(cseMachine);
            System.out.println(cseMachine);
        }
    }

    private static String getFilenameFromCommandLineArguments(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Main <filename>");
            return null;
        }
        return args[0];
    }
}
