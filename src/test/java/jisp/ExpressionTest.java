package jisp;

import jaskell.parsec.common.State;
import jaskell.parsec.common.TxtState;
import jisp.ast.*;
import jisp.parsers.Parser;
import org.junit.Assert;
import org.junit.Test;

import java.io.EOFException;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/23 16:30
 */
public class ExpressionTest {
    private final static Env env = new Env();

    static {
        env.put("+", new Add());
        env.put("-", new Sub());
        env.put("*", new Product());
        env.put("/", new Divide());
    }

    @Test
    public void testAdd() throws EOFException, ParserException {
        String source = "(+ 1 2 3)";
        State<Character> state = new TxtState(source);
        Parser parser = new Parser();
        Object ast = parser.parse(state);
        Double result = ((Element) ast).eval(env);
        Double expect = 6d;
        Assert.assertEquals(expect, result);
    }

    @Test
    public void testProduct() throws EOFException, ParserException {
        String source = "(* 1 2 3)";
        State<Character> state = new TxtState(source);
        Parser parser = new Parser();
        Object ast = parser.parse(state);
        Double result = ((Element) ast).eval(env);
        Double expect = 6d;
        Assert.assertEquals(expect, result);
    }

    @Test
    public void testPloy() throws EOFException, ParserException {
        String source = "(* 5 (+ 2 3))";
        State<Character> state = new TxtState(source);
        Parser parser = new Parser();
        Object ast = parser.parse(state);
        Double result = ((Element) ast).eval(env);
        Double expect = 25d;
        Assert.assertEquals(expect, result);
    }

    @Test
    public void testMorePloy() throws EOFException, ParserException {
        String source = "(* (* 3 3) (+ 2 3))";
        State<Character> state = new TxtState(source);
        Parser parser = new Parser();
        Object ast = parser.parse(state);
        Double result = ((Element) ast).eval(env);
        Double expect = 45d;
        Assert.assertEquals(expect, result);
    }
}
