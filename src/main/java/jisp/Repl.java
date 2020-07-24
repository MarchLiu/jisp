package jisp;

import jaskell.parsec.ParsecException;
import jaskell.parsec.common.Parsec;
import jisp.ast.*;
import jisp.parsers.NumberParser;
import jisp.parsers.Parser;

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
    private final static Env env = new Env();
    static {
        env.put("+", new Add());
        env.put("-", new Sub());
        env.put("*", new Product());
        env.put("/", new Divide());
    }

    private static final String prmt = ">> ";
    private static final Parser parser = new Parser();
    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.print(prmt);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();
            try {
                Object data = parser.parse(line);
                if(data instanceof Element) {
                    data = ((Element)data).eval(env);
                }
                System.out.println(data);
            } catch (ParsecException err) {
                System.out.println(String.format("invalid syntax [%s] error [%s]", line, err.getMessage()));
            } catch (ParserException err) {
                System.out.println(String.format("eval [%s] error [%s]", line, err.getMessage()));
            }
        }
    }
}
