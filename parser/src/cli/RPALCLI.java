package cli;

import compiler.RPALCompiler;
import cse_machine.Control;
import cse_machine.CseMachine;
import cse_machine.elements.control.ControlElement;

public class RPALCLI {
    public static void main(String[] args) {
        CLIArguments cliArgs = CLIArguments.parse(args);
        String fileContent = FileLoader.load(cliArgs.getFilename());

        RPALCompiler compiler = new RPALCompiler(fileContent);
        compiler.compile();

        if (cliArgs.shouldPrintAST()) System.out.println(compiler.getAST());
        if (cliArgs.shouldPrintST()) System.out.println(compiler.getST());
        if (cliArgs.shouldPrintControlStructures()) System.out.println(compiler.getControlStructures());
        if (cliArgs.shouldPrintCSEStates()) runCSE(compiler.getCseMachine());
    }

    private static void runCSE(CseMachine cse) {
        Control control = cse.getControl();
        while (control.hasNext()) {
            System.out.println("--------------------------------------------------------");
            ControlElement element = control.next();
            element.accept(cse);
            System.out.println(cse);
        }
    }
}

