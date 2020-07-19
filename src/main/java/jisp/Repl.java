package jisp;

import jaskell.expression.Env;
import jaskell.expression.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/16 22:44
 */
public class Repl {
    private static final String prmt = ">> ";
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        Env env = new Env();
        while (true) {
            System.out.print(prmt);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();

        }
    }
}
