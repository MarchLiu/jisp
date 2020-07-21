package jisp;

import jaskell.parsec.ParsecException;
import jaskell.parsec.common.Parsec;
import jisp.parsers.NumberParser;

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
    private static final Parsec<Double, Character> parser = new NumberParser();
    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.print(prmt);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();
            try {
                Double result = parser.parse(line);
                System.out.println(result);
            } catch (ParsecException err) {
                System.out.println(String.format("invalid number [%s] error [%s]", line, err.getMessage()));
            }
        }
    }
}
