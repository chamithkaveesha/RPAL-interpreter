import cli.CLIArguments;
import compiler.RPALCompiler;
import cse_machine.CseMachine;
import tree.ast.ASTNode;
import tree.st.STNode;
import tree.transform.ControlStructure;
import utils.FCNSNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CLIArguments cliArgs;
        try {
            cliArgs = CLIArguments.parse(args);
        } catch (IllegalArgumentException e) {
            System.out.println("Usage: java Main <filename> [-ast] [-st] [-control] [-cse]");
            System.out.println("Error: " + e.getMessage());
            return;
        }

        String fileContent;
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(cliArgs.getFilename())));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        RPALCompiler compiler = new RPALCompiler(fileContent);
        compiler.compile();

        if (cliArgs.shouldPrintAST()) {
            FCNSNode<ASTNode> ast = compiler.getAST();
            System.out.println(ast);
        }

        if (cliArgs.shouldPrintST()) {
            FCNSNode<STNode> st = compiler.getST();
            System.out.println(st);
        }

        if (cliArgs.shouldPrintControlStructures()) {
            List<ControlStructure> controlStructures = compiler.getControlStructures();
            for (ControlStructure cs : controlStructures) {
                System.out.println(cs);
            }
        }

        CseMachine machine = compiler.getCseMachine();
        while (machine.getControl().hasNext()) {
            if (cliArgs.shouldPrintCSEStates()) {
                System.out.println("--------------------------------------------------------");
                System.out.println("Before step:\n" + machine);
            }

            machine.getControl().next().accept(machine);

            if (cliArgs.shouldPrintCSEStates()) {
                System.out.println("After step:\n" + machine);
            }
        }
    }
}
