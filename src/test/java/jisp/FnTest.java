package jisp;

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
 * @since 2020/08/05 16:18
 */
public class FnTest {
    private final Env env;
    private final Parser parser = new Parser();

    public FnTest() {
        env = new Env();
        env.put("def", new Def());
        env.put("if", new If());

        env.put("fn", new Fn());
        env.put("recur", new Recur());

        env.put("and", new And());
        env.put("or", new Or());
        env.put("==", new Eq());
        env.put("!=", new NotEq());
        env.put(">", new Great());
        env.put("<", new Less());
        env.put(">=", new GreatOrEquals());
        env.put("<=", new LessOrEquals());

        env.put("+", new Add());
        env.put("-", new Sub());
        env.put("*", new Product());
        env.put("/", new Divide());
    }

    @Test
    public void testDefine() throws EOFException, ParserException {
        env.eval(parser.parse("(def add (fn (x y) (+ x y)))"));

        Assert.assertEquals(9.42, env.eval(parser.parse("(add 3.14 6.28)")));
    }

    @Test
    public void testRecur() throws EOFException, ParserException {
        env.eval(parser.parse("(def increment (fn (x) (if (< x 10) (recur (* 2 x)) x)))"));

        Assert.assertEquals(16.0, env.eval(parser.parse("(increment 2)")));
    }

    @Test
    public void testStatic() throws EOFException, ParserException {
        env.eval(parser.parse("(def add (fn (x y) (+ x y)))"));

        env.eval(parser.parse("(def step6 (fn (x) (if (< x 10) (recur (add 6 x)) x)))"));

        Assert.assertEquals(14.0, env.eval(parser.parse("(step6 2)")));
    }
}
