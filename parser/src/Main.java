import st.RPALStandardizer;
import tree.ast.ASTBuilder;
import tree.ast.ASTNode;
import parser.Parser;
import parser.RPALParser;
import scanner.*;
import scanner.Scanner;
import tree.st.STBuilder;
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
        System.out.println(new RPALStandardizer().getST(ast));

    }

    private static String getFilenameFromCommandLineArguments(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Main <filename>");
            return null;
        }
        return args[0];
    }
}
