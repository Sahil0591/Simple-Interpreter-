import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class Task1Tester {

    public static void main(String[] args) throws IOException {

        String pathToTests = "./task1tests/";
        File dir = new File(pathToTests);
        File[] directoryListing = dir.listFiles((file, name) -> name.toLowerCase().endsWith(".simp"));
        if (directoryListing != null) {
            for (File child : directoryListing) {

                System.out.println("Trying testcase " + child.getName());

                SimpleLangParser.ProgContext tree;
                try {
                    CharStream input = CharStreams.fromFileName(pathToTests + child.getName());
                    SimpleLangLexer lexer = new SimpleLangLexer(input);
                    CommonTokenStream tokens = new CommonTokenStream(lexer);
                    SimpleLangParser parser = new SimpleLangParser(tokens);
                    tree = parser.prog();
                } catch (Exception e) {
                    System.err.println("Exception when parsing " + child.getName());
                    continue;
                }

                StringBuilder sb = new StringBuilder();

                try (FileReader fr = new FileReader(pathToTests + child.getName() + ".args"); BufferedReader br = new BufferedReader(fr)) {
                    String line;
                    while ((line = br.readLine()) != null) {

                        PrintStream old = System.out;
                        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); PrintStream ps = new PrintStream(baos)) {

                            System.setOut(ps);
                            SimpleLangInterpreter interpreter = new SimpleLangInterpreter();
                            Integer mainReturnValue = interpreter.visitProgram(tree, line.isEmpty() ? new String[0] : line.trim().split("\\s+"));
                            System.out.println();
                            System.out.println("NORMAL_TERMINATION");
                            System.out.println(mainReturnValue);
                            System.out.flush();
                            System.setOut(old);
                            sb.append(baos);

                        } catch (Exception e) {

                            System.err.println("Exception when feeding " + line + " into " + child.getName());
                            System.setOut(old);
                            continue;
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Exception when reading arguments for " + child.getName());
                    continue;
                }

                try (FileReader fr = new FileReader(pathToTests + child.getName() + ".answers"); BufferedReader br = new BufferedReader(fr)) {
                    String content = br.lines().collect(Collectors.joining("\n"));
                    if (!content.trim().replace("\r", "").equals(sb.toString().trim().replace("\r", ""))) {
                        System.err.println("Incorrect output for " + child.getName());
                        System.err.println("Answers:\n\n" + content);
                        System.err.println("Output:\n\n" + sb.toString());
                    }
                } catch (Exception e) {
                    System.err.println("Exception when reading answers for " + child.getName());
                    continue;
                }
            }
        } else {
            System.err.println("Failed to find any testcases!");
        }
    }
}