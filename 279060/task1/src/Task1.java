import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class Task1 {

    public static void main(String[] args) throws IOException {
        // create a CharStream that reads from standard input
        CharStream input = CharStreams.fromStream(System.in);

        // create a lexer that feeds off of input CharStream
        SimpleLangLexer lexer = new SimpleLangLexer(input);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        SimpleLangParser parser = new SimpleLangParser(tokens);
        SimpleLangParser.ProgContext tree = parser.prog(); // begin parsing at prog rule

        SimpleLangInterpreter interpreter = new SimpleLangInterpreter();
        Integer mainReturnValue = interpreter.visitProgram(tree, args);
        System.out.println();
        System.out.println("NORMAL_TERMINATION");
        System.out.println(mainReturnValue);

    }
}